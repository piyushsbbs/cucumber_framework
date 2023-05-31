package schmeValidators;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.matcher.RestAssuredMatchers;
public class swgger {
	@Test
	public void schema(){
		RestAssured.baseURI="https://petstore.swagger.io";
		Response str=given().log().all().header("Accept","application/xml").queryParam("status", "available")
		.when().get("v2/pet/findByStatus");
		
		str.then().assertThat().statusCode(200).log().all().body(matchesXsdInClasspath("demoxsd.xsd"));
	}

}
