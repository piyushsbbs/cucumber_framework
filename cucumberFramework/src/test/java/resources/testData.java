package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.locationpojo;
import pojo.requestpojo;

public class testData {

	public requestpojo testdata(String name, String url)
	{
		requestpojo req=new requestpojo();
		req.setAccuracy(50);
		req.setAddress("Dummy test");
		req.setLanguage("French-IN");
		req.setName(name);
		req.setPhone_number("098765735");
		req.setWebsite(url);
		List<String> lis=new ArrayList<String>();
		lis.add("test");
		lis.add("hhh");
		req.setTypes(lis);
		locationpojo l=new locationpojo();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		req.setLocation(l);
		return req;
	}
	
	public String deletepayload(String placeid)
	{
		String payload="{\r\n" + 
				"    \"place_id\": \""+placeid+"\"\r\n" + 
				"}";
		return payload;
	}
}
