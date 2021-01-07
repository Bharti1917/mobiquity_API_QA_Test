package com.api.testing.testbase;

import org.junit.BeforeClass;

import io.restassured.RestAssured;

public class TestBase {

	@BeforeClass
	public static void init()
	{
		RestAssured.baseURI="https://jsonplaceholder.typicode.com";
	}
}
