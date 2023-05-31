package schmeValidators;

import io.restassured.RestAssured;
import io.restassured.response.Response;


import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;


import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.path.json.JsonPath;
public class swgger {
	@Test
	public void schema(){
		
			String place = null;
			String newchange="test address, 1234";
			RestAssured.baseURI = "https://rahulshettyacademy.com";
			String res = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
					.body("{\r\n" + 
							"    \"location\": {\r\n" + 
							"        \"lat\": -38.383494,\r\n" + 
							"        \"lng\": 33.427362\r\n" + 
							"    },\r\n" + 
							"    \"accuracy\": 50,\r\n" + 
							"    \"name\": \"Frontline house\",\r\n" + 
							"    \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
							"    \"address\": \"29, side layout, cohen 09\",\r\n" + 
							"    \"types\": [\r\n" + 
							"        \"shoe park\",\r\n" + 
							"        \"shop\"\r\n" + 
							"    ],\r\n" + 
							"    \"website\": \"http://google.com\",\r\n" + 
							"    \"language\": \"French-IN\"\r\n" + 
							"}").when().post("maps/api/place/add/json").then().assertThat().statusCode(200)
					.header("server", "Apache/2.4.41 (Ubuntu)").body("scope", equalTo("APP")).extract().response().body()
					.asString();
			JsonPath jsp = new JsonPath(res);
			place = jsp.getString("place_id");
			System.out.println(res);
			System.out.println(jsp.getString("place_id"));

			given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
					.body("{\r\n" + "\"place_id\":\"" + place + "\",\r\n" + "\"address\":\"" + newchange + "\",\r\n"
							+ "\"key\":\"qaclick123\"\r\n" + "}")
					.when().put("maps/api/place/update/json").then().log().body().assertThat().statusCode(200)
					.body("msg", equalTo("Address successfully updated"));
			
			given().log().all().queryParam("key", "qaclick123")
			.queryParam("place_id",place).when().get("maps/api/place/get/json").
			then().assertThat().statusCode(200).log().all().body(matchesJsonSchemaInClasspath("\\src\\test\\java\\schmeValidators\\jsonschema.json"));



		}
}
