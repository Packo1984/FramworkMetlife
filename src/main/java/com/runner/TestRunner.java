package main.java.com.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//tags= option below specifies which scenarios to run. Change tags= { "@PositiveScenario" } or @negativeScenario, etc., 
//Cucumber will run only those feature files specific to given tags.
//When we define multiple  tags in runner class in below form ,it will be defined with the use of logical operator:
//1. tags = {“@tag1”, “@tag2”} : means AND condition. –It says that scenarios matching both these tag needs to be executed.
//2. tags = {“@tag1, @tag2”} : means OR condition. — It says that scenarios matching any of this tag needs to be executed.
@CucumberOptions(plugin = { "pretty","junit:target/cucumber-reports/Cucumber.xml" },features = "src/features", glue = { "stepdefinitions" }, tags = {"@ejercicio"})
public class TestRunner extends AbstractTestNGCucumberTests {

}
 