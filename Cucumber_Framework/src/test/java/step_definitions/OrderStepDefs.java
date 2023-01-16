package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class OrderStepDefs extends BaseStep {

    @Given("the user navigates to {string} page")
    public void the_user_navigates_to_page(String string) {
        pages.webOrdersPage().navigateTo(string);
    }

    // | Product   | Quantity | Price per unit | Discount |
    @When("the user enters product information")
    public void the_user_enters_product_information(List<Map<String, String>> dataTable) {
        Map<String, String> values = dataTable.get(0);
        pages.orderPage().setProductType(values.get("Product"));
        pages.orderPage().enterQuantity(values.get("Quantity"));
        pages.orderPage().enterPricePerUnit(values.get("Price per unit"));
        pages.orderPage().enterDiscount(values.get("Discount"));
    }

    @When("the user enters product information: Product {string}, Quantity {string}, Price per unit  {string}, Discount  {string}")
    public void the_user_enters_product_information_Product_Quantity_Price_per_unit_Discount(String product, String quantity, String pricePerUnit, String discount) {
        pages.orderPage().setProductType(product);
        pages.orderPage().enterQuantity(quantity);
        pages.orderPage().enterPricePerUnit(pricePerUnit);
        pages.orderPage().enterDiscount(discount);
    }

    @When("the user click on calculate button")
    public void the_user_click_on_calculate_button() {
        pages.orderPage().clickToCalculate();
    }

    @Then("the user should verify that total {int} is displayed")
    public void the_user_should_verify_that_total_is_displayed(Integer totalValue) {
        Assert.assertEquals(String.valueOf(totalValue), pages.orderPage().getTotal());
    }


    //| Customer name | Street  | City     | State    | Zip   |
    @When("the user enters address information:")
    public void the_user_enters_address_information(List<Map<String, String>> dataTable) {
        Map<String, String> values = dataTable.get(0);
        pages.orderPage().enterAddressInformation(values.get("Customer name"),
                values.get("Street"),
                values.get("City"),
                values.get("State"),
                values.get("Zip"));
    }

    //| Customer name | Street  | City     | State    | Zip   |
    @When("the user enters address information: Customer Name {string}, Street {string}, City {string}, State {string}, Zip {string}")
    public void the_user_enters_address_information(String customerName, String street, String city, String state, String zip) {
        pages.orderPage().enterAddressInformation(
                customerName,
                street,
                city,
                state,
                zip);
    }

    @When("the user enters payment information:")
    public void the_user_enters_payment_information(List<Map<String, String>> dataTable) {
        Map<String, String> paymentInfo = dataTable.get(0);
        pages.orderPage().enterPaymentInformation(paymentInfo.get("Card"),
                paymentInfo.get("Card Nr:"),
                paymentInfo.get("Expire date (mm/yy)"));
    }

    @When("the user enters payment information: Card {string}, Card Number {string}, Expire Date with mm yy date format {string}")
    public void the_user_enters_payment_information(String card, String cardNumber, String expireDate) {
        pages.orderPage().enterPaymentInformation(card,
                cardNumber,
                expireDate);
    }

    @Then("the user click on process button")
    public void the_user_click_on_process_button() {
        pages.orderPage().clickOnProcessButton();
    }


}
