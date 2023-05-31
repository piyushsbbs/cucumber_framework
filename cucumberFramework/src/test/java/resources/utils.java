package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class utils {
	 
	static RequestSpecification  reqspec;
	public RequestSpecification setuprequest() throws FileNotFoundException {
		if (reqspec ==null)
		{
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
		 reqspec = new RequestSpecBuilder().setBaseUri(readproperty("BaseURI"))
				.addHeader("Content-Type", "application/json").addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
		return reqspec;
		}
		return reqspec;
	}

	public String readproperty(String key) {
		try {
			Properties prp = new Properties();
			FileInputStream fs = new FileInputStream("src/test/java/resources/config.properties");
			prp.load(fs);
			return prp.getProperty(key);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String return_value(Response response, String key)
	{
		String res=response.asString();
		System.out.println(res);
		JsonPath jp=new JsonPath(res);
		return jp.getString(key);

		
	}

}
