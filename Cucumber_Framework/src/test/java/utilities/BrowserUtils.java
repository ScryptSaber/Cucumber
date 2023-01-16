package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

public class BrowserUtils {
    /**
     * @param name screenshot name
     * @return path to the screenshot
     */

    public static String getScreenshot(String name) {
        //adding date and time to screenshot name, to make screenshot unique
        name = new Date().toString().replace(" ", "_").replace(":", "-") + "_" + name;

        //path where we are gonna store a screenshot
        String path = "";

        if (System.getProperty("os.name").toLowerCase().contains("mac")) {
            path = System.getProperty("user.dir") + "/test-output/screenshots/" + name + ".png";
        } else {
            path = System.getProperty("user.dir") + "\\test-output\\screenshots\\" + name + ".png";
        }

        System.out.println("OS name: " + System.getProperty("os.name"));
        System.out.println("Screenshot is here: " + path);

        /**
         * Since our reference type is a WebDriver
         * We cannot see methods from TakesScreenshot interface
         * that's why do casting
         */

        TakesScreenshot takesScreenshot = (TakesScreenshot) Driver.getDriver();
        //Take screenshot of web browser, and save it as a file
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        // where screenshot will be saved
        File destination = new File(path);

        try {
            //Copy file to the previously specified location
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;


    }

    /**
     * Verifies whether the element is displayed on page
     * fails if the element is not found or not displayed
     *
     * @param element
     */
    public static void verifyElementDisplayed(WebElement element) {
        try {
            Assert.assertTrue(element.isDisplayed(), "Element not visible: " + element);
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found: " + element);
        }
    }

    /**
     * Performs double click action on an element
     *
     * @param element
     */
    public void doubleClick(WebElement element) {
        new Actions(Driver.getDriver()).doubleClick(element).build().perform();
    }

    /**
     * Performs hover over mouse action on an element
     *
     * @param element
     */
    public static void hoverOver(WebElement element) {
        new Actions(Driver.getDriver()).moveToElement(element);
    }

    /**
     * Performs thread sleep for the desired seconds
     */
    public static void wait(int secs) {
        try {
            Thread.sleep(1000 * secs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Navigates to the target window
     *
     * @param targetTitle
     */
    public static void switchToWindow(String targetTitle) {
        String origin = Driver.getDriver().getWindowHandle();
        for (String handle : Driver.getDriver().getWindowHandles()) {
            Driver.getDriver().switchTo().window(handle);
            if (Driver.getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        Driver.getDriver().switchTo().window(origin);
    }

    /**
     * Waits for visibility of an element
     *
     * @param element
     * @return WebElement
     */
    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.visibilityOf(element));

    }

    /**
     * Waits for visibility of an element
     *
     * @param locator
     * @return WebElement
     */
    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits for clickablility of an element
     *
     * @param element
     * @return WebElement
     */
    public static WebElement waitForClickability(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits for clickablility of an element
     *
     * @param locator
     * @return WebElement
     */
    public static WebElement waitForClickablility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));

    }

    public static void navigateBackAndForwardToDismissAds() {
        Driver.getDriver().navigate().back();
        Driver.getDriver().navigate().forward();

    }


}
