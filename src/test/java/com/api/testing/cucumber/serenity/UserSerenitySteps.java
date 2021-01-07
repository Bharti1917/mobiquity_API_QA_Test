package com.api.testing.cucumber.serenity;

import java.util.HashMap;
import java.util.List;

import com.api.testing.constants.EndPoints;
import com.api.testing.testbase.TestBase;
import com.api.testing.utils.ReusableSpecifications;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;


public class UserSerenitySteps extends TestBase   {
	
	@Step("Validating response of get users API with username:{0}")
	public ValidatableResponse getUser(String username)
	{
		
		return SerenityRest
			 	.rest()
				.given()
				.queryParam("username", username)
				.when()
				.get(EndPoints.GET_USERS)
				.then()
				.spec(ReusableSpecifications.getGenericResponseSpec())
				.log()
				.all();
		
	}
	
	@Step("Fetching the user details with username:{0} ")
	public HashMap<String,Object> getUserDetailsByUsername(String username)
	{
		 
		System.out.println("username in userserenity"+ username);
		String p1="findAll{it.username=='";
	     String p2= "'}.get(0)";
	     
		return SerenityRest
			 	.rest()
				.given()
				.queryParam("username", username)
				.when()
				.get(EndPoints.GET_USERS)
				.then()
				.log()
				.all()
				.extract()
				.path(p1+username+p2);
		
	}
	@Step("Fetching the user Id with username:{0}  ")
	public String getUserIdByUsername(String username)
	{
	
			
		List<String> userIds= SerenityRest
			 	.rest()
				.given()
				.queryParam("username", username)
				.when()
				.get(EndPoints.GET_USERS)
				.then()
				.log()
				.all()
				.extract()
				.path("id");
		String userId =userIds.toString().replaceAll("[\\p{Ps}\\p{Pe}]", "");
		return userId;
		
	}
	
}
