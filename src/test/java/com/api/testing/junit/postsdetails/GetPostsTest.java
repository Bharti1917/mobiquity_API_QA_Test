package com.api.testing.junit.postsdetails;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Test;
import org.junit.runner.RunWith;

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
public class GetPostsTest  extends TestBase{
	
	 private static String username;
	
		
	  public String getUsername()
	  { return username; }
	    
	  public void setUsername(String username)
	  { this.username = username; }
	
	UserSerenitySteps uSteps=new UserSerenitySteps();
	PostsSerenitySteps pSteps=new PostsSerenitySteps();
	String userId = null;
	
	@WithTags(
			{
	@WithTag("usersfeature:SMOKE"),
	@WithTag("usersfeature:POSITIVE")
			}
	)
	@Title("This will validate the response code 200 of get posts API")
	@Test
	public void verifytheStatusCodeis200()
	{
		
	     pSteps.getPosts(userId)
	     .statusCode(200);	
		
	}	
		
	@WithTags(
			{
	@WithTag("usersfeature:INTEGRATION"),
	@WithTag("usersfeature:POSITIVE")
			}
	)	
 @Title("This will validate the postIds from post details API")
 @Test  
	 public void getPostIds() {
	 
	 userId= uSteps.getUserIdByUsername(username);
	 assertThat(pSteps.getPostsDetailsByUserId(userId),is(notNullValue()));
		  		  
		 }
}
