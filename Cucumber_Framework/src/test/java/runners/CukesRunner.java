package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "rerun:target/rerun.txt"
        },
        features = "src/test/resources/features",
        glue = "step_definitions",
        tags = "@smoke@login",
        dryRun = false
)

public class CukesRunner {
}
