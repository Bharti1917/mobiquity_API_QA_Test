package com.api.testing.junit.commentsDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.api.testing.cucumber.serenity.CommentsSerenity;
import com.api.testing.cucumber.serenity.PostsSerenitySteps;
import com.api.testing.cucumber.serenity.UserSerenitySteps;
import com.api.testing.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import net.thucydides.junit.annotations.UseTestDataFrom;

@UseTestDataFrom("testData\\userinfo.csv")
@RunWith(SerenityParameterizedRunner.class)
public class GetCommentsTest  extends TestBase{
	
	 private static String username;
		
		
	  public String getUsername()
	  { return username; }
	    
	  public void setUsername(String username)
	  { this.username = username; }
	
	CommentsSerenity comser = new CommentsSerenity();
	UserSerenitySteps uSteps=new UserSerenitySteps();
	PostsSerenitySteps pSteps=new PostsSerenitySteps();
	String userId = null;
	
	
	@WithTags(
			{
	@WithTag("usersfeature:INTEGRATION"),
	@WithTag("usersfeature:POSITIVE")
			}
	)
	@Title("This is to validate the format of all emails for each comment")
	@Test
	public void getCommentsIds()
	{
		userId= uSteps.getUserIdByUsername(username);
		comser.validateEmails(userId);
			
	}
}
	
	
