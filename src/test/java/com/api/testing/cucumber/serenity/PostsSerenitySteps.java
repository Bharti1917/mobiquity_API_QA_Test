package com.api.testing.cucumber.serenity;
import java.util.List;

import com.api.testing.constants.EndPoints;
import com.api.testing.utils.ReusableSpecifications;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;


public class PostsSerenitySteps {
	@Steps
	UserSerenitySteps uSteps=new UserSerenitySteps();
	 
	  	 
	
	@Step("Validating status code of get posts API")
	public ValidatableResponse getPosts(String userId)
	{
		return SerenityRest
			 	.rest()
				.given()
				.queryParam("userId", userId)
				.when()
				.get(EndPoints.GET_POSTS)
				.then()
				.spec(ReusableSpecifications.getGenericResponseSpec())
				.log()
				.all();
		
	}
	
	@Step("Fetching the posts information with userId:{0} ")
	public List<String> getPostsDetailsByUserId(String userId)
	{
		return SerenityRest
			 	.rest()
				.given()
				.queryParam("userId", userId)
				.when()
				.get(EndPoints.GET_POSTS)
				.then()
				.log()
				.all()
				.extract()
				.path("id");
		
	}

	
	
}
