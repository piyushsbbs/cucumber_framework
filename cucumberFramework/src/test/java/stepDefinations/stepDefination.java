package stepDefinations;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.Assert.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import pojo.locationpojo;
import pojo.requestpojo;
import resources.paths;
import resources.testData;
import resources.utils;

public class stepDefination extends utils {
	Response respon;
	static String value;
	RequestSpecification resp;
	@Given("add place with {string} {string}")
	public void add_place(String name, String url) throws FileNotFoundException {
		testData td=new testData();
		resp=given().spec(setuprequest()).when().body(td.testdata(name, url));
	
	}
	@Given("Delete place")
	public void delete_place() throws FileNotFoundException {
		testData td=new testData();

		resp=given().spec(setuprequest()).when().body(td.deletepayload(value));
	
	}
	@When("call {string} with {string} request")
	public void call_with_request(String path, String method) {
		paths psrt=paths.valueOf(path);
	String ste=	psrt.getresource();
	    // Write code here that turns the phrase above into concrete actions
	if(path.equalsIgnoreCase("basecreatelocation") || path.equalsIgnoreCase("baseddeletelocation"))
	{
	   respon=resp.when().post(psrt.getresource()).then().extract().response();
	}
	else if(path.equalsIgnoreCase("basegetlocation"))
	{
		respon=resp.when().get(psrt.getresource()).then().extract().response();
	}
	
	}
	@Then("status should {int}")
	public void status_should(int int1) {
	    // Write code here that turns the phrase above into concrete actions
		int code=respon.getStatusCode();
		respon.then().body(matchesJsonSchemaInClasspath("jsonschema.json"));
	   assertEquals(int1,code);
	}
	@Then("reponse body {string} must have value {string}")
	public void reponse_body_must_have_value(String string, String string2) {
	String resbody=respon.asString();
	JsonPath jsp=new JsonPath(resbody);
	assertEquals(string2,jsp.getString(string));
	}

	@Then("Verify the {string} from {string} request")
	public void verify_the_from_request(String key, String method) throws FileNotFoundException {
		value=return_value(respon, "place_id");
	System.out.println(value);
		resp=given().spec(setuprequest()).queryParam("place_id", value);
		call_with_request( method,  "hj");
		String value2=return_value(respon, "name");
		assertEquals("jkjd", value2);
	}
}
