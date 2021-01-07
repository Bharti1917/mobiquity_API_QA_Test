package com.api.testing.junit.usersdetails;

import java.util.HashMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.api.testing.cucumber.serenity.UserSerenitySteps;
import com.api.testing.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import net.thucydides.junit.annotations.UseTestDataFrom;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@UseTestDataFrom("testData\\userinfo.csv")
@RunWith(SerenityParameterizedRunner.class)
public class GetUsersTest extends TestBase {
	
	 private static String username;
		
	  public String getUsername()
	  { return username; }
	  
	  
	  public void setUsername(String username)
	  { this.username = username; }
	 //creating instance of UserSerenitySteps class
	  
		@Steps
		UserSerenitySteps uSteps=new UserSerenitySteps();
		
		  @WithTags( {
		  
		  @WithTag("usersfeature:SMOKE"),
		  
		  @WithTag("usersfeature:POSITIVE") } )
		  
		  @Title("This will validate the response code 200 of get users API")
		  @Test
		  public void verifyIftheStatusCodeIs200() {
			  System.out.println(username+" as in verifycode method");
		  
		  uSteps.getUser(username).statusCode(200);
		  }
		  
		
		  @WithTags( {
		  
		  @WithTag("usersfeature:SMOKE"),
		  
		  @WithTag("usersfeature:NEGATIVE") } )
		  
		  @Title("This will validate the response code  500 of get users API")
		  @Test
		  public void verifyIftheStatusCodeIsNot200() {
		  uSteps.getUser(username) .statusCode(500);
		  }
		  
		  
		  @WithTags( {
		  
		  @WithTag("usersfeature:INTEGRATION"),
		  
		  @WithTag("usersfeature:POSITIVE") } )
		  
		  @Title("This will validate the username of get users api response as per the requirement")
		  @Test
		  public void getUserDetails() {
		  HashMap<String,Object> userDetails= uSteps.getUserDetailsByUsername(username);
		  
		  System.out.println(userDetails); 
		  assertThat(userDetails,hasValue(username));
		  
		  }
		 	
		  @WithTags( {
			  
			  @WithTag("usersfeature:INTEGRATION"),
			  
			  @WithTag("usersfeature:POSITIVE") } )
			  
			  @Title("This will validate that user id value is not null")
		  	  @Test
			  public void validateUserIdIsNotNull() {
			
			  assertThat(uSteps.getUserIdByUsername(username),is(notNullValue()));
			 
			  }
		

}
