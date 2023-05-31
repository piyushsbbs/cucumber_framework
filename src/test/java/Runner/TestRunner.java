package Runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features"
,glue= {"stepDefinations"}, tags= ("@test1"),
plugin = { "pretty" , "json:target/jsonReports/cucumber-report.json" },
monochrome = true)
public class TestRunner {
	
}
