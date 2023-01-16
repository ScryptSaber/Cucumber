package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.ConfigReader;
import utilities.Driver;

public class Hooks {
    @Before
    public void setup(Scenario scenario) {
        System.out.println("::---- Starting Test Automation ----::");
        System.out.println("Browser type: " + ConfigReader.getProperty("browser"));
        System.out.println("Environment: " + ConfigReader.getProperty("url"));
        System.out.println("Test Scenario: " + scenario.getName());
        Driver.getDriver().manage().window().maximize();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) Driver.getDriver();
            byte[] image = takesScreenshot.getScreenshotAs(OutputType.BYTES);
            scenario.attach(image, "image/png", ",Byte of SmartBear");
        }
        Driver.closeDriver();
    }
}
