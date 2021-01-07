package com.api.testing.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;

public class ReusableSpecifications {
	
	public static RequestSpecBuilder rspec;
	public static RequestSpecification requestSpecification;
	
	public static ResponseSpecBuilder respec;
	public static ResponseSpecification responseSpecification;
	
	//to set common specifications for any request and response
	
	public static RequestSpecification getGenericRequestSpec()
	{
		rspec = new RequestSpecBuilder();
		rspec.setContentType(ContentType.JSON);
		requestSpecification= rspec.build();
		
		return requestSpecification;
		
	}

	
	public static ResponseSpecification getGenericResponseSpec()
	{
		respec = new ResponseSpecBuilder();
		respec.expectHeader("Content-Type","application/json; charset=utf-8");
		respec.expectHeader("Transfer-encoding","chunked");
		respec.expectResponseTime(lessThan(5L),TimeUnit.SECONDS);
		
		responseSpecification=respec.build();
		return responseSpecification;
		
	}
}
