package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.testng.Assert;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

public class LoginProcessStepDefs extends BaseStep {

    @Given("the user is on the login page")
    public void user_is_on_the_login_page() {
        String url = ConfigReader.getProperty("url");
        Driver.getDriver().get(url);
        System.out.println("Open :: " + url);
        BrowserUtils.wait(1);

    }

    @When("the user enters username {string}")
    public void user_enters_username(String usernameValue) {
        pages.loginPage().enterUserNameValue(usernameValue);
    }

    @When("the user enters password as {string}")
    public void user_enters_password_as(String passwordValue) {
        pages.loginPage().enterPasswordValue(passwordValue);
    }

    @When("the user clicks on the login")
    public void user_clicks_on_the_login_button() {
        pages.loginPage().clickLoginButton();
    }

    @Then("the user should be logged in")
    public void the_user_should_be_logged_in() {
        BrowserUtils.wait(1);
        Assert.assertEquals(pages.webOrdersPage().getCurrentUrl(), "http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/");
        throw new io.cucumber.java.PendingException();
    }

    @Then("the user arrives at the home page and welcome text containing the username {string}")
    public void the_user_arrives_at_the_home_page_and_welcome_text_containing_the_username(String enteredUserNameValue) {
        String displayedUsernameValue = pages.webOrdersPage().getDisplayedUserName();
        Assert.assertTrue(displayedUsernameValue.contains(enteredUserNameValue));
        System.out.println(displayedUsernameValue);
        System.out.println(enteredUserNameValue);
    }

    @Then("the user should not be able to login and {string} error must be displayed")
    public void the_user_should_not_be_able_to_login_and_error_must_be_displayed(String errorMessage) {
        String actualErrorMessage = pages.loginPage().getErrorMessage();
        Assert.assertTrue(actualErrorMessage.contentEquals(errorMessage));
    }


}