package com.api.testing.cucumber.serenity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.validator.routines.EmailValidator;
import org.junit.Assert;

import com.api.testing.constants.EndPoints;
import com.api.testing.utils.ReusableSpecifications;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class CommentsSerenity {
	
	
	PostsSerenitySteps postsSteps = new PostsSerenitySteps();
	List<String> postIds=null; 
	
	@Step("Fetch list of emails for each comment")
	public  List<String> getEmailsForEachComments(String userId)
	
	{ 
		postIds =postsSteps.getPostsDetailsByUserId(userId);
		List<String> emailList =new  ArrayList<String>();
		for (int i=0;i<postIds.size();i++)
			
		{
			 Response response =SerenityRest
			 	.rest()
				.given()
				.queryParam("postId", postIds.get(i))
				.when()
				.get(EndPoints.GET_COMMENTS);
			 
			String emails= response.then()
				.spec(ReusableSpecifications.getGenericResponseSpec())
				.log()
				.all()
				.extract()
				.path("email").toString();
			emailList.add(emails);	
			
		
		}
		
		return emailList;
	
	
	}

	
	@Step("Validate the format of each email Id for given comments")
	public void validateEmails(String userId)
	{
       //creating instance of CommentsSerenity class
		CommentsSerenity comser = new CommentsSerenity();
		List<String> emailsNew=comser.getEmailsForEachComments(userId);
	
		String emailIds = emailsNew.toString().replaceAll("[\\p{Ps}\\p{Pe}]", "");;
		List<String> convertedemailList = Arrays.asList(emailIds.split(", ", -1));
		for (int i=0; i<convertedemailList.size();i++)
			 {
				 boolean valid =EmailValidator.getInstance().isValid(convertedemailList.get(i)); 
				 Assert.assertTrue("Format of email Id"+convertedemailList.get(i)+"is valid",valid);
			     System.out.println("Test Passed: "+"Format of email Id "+convertedemailList.get(i)+" is valid" );
			 }
	}

}
