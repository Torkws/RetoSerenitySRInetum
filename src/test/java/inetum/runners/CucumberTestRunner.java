package inetum.runners;

import net.serenitybdd.cucumber.CucumberWithSerenity;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "inetum.stepdefinitions",
        plugin = {
                "pretty",
                "json:target/cucumber-reports/cucumber.json"
        },
        dryRun = false,
        monochrome = false,
        tags = "@saucedemo"
)
public class CucumberTestRunner {

}




