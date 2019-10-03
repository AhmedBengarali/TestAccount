package acceptancetests.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * This class is used for running the cucumber tests.
 * By default all feature files and scenarios under src/test/java/acceptancetests/features
 * are taken under consideration. This is where the scenarios for local tests (e.g. by
 * the developers should be placed.
 */

@CucumberOptions(features = {"src/test/java/acceptancetests/features"} , 
        plugin = {"json:target/cucumber.json","html:target/site/cucumber-pretty"},
        glue = "acceptancetests/steps",
        tags = {"@ web"})
public class TestRunner extends AbstractTestNGCucumberTests{

}
