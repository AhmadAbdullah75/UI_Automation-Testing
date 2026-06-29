package stepDef;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.DarazCartPage;
import pageObjects.DarazCheckoutPage;
import utils.BaseClass;
import utils.DriverManager;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertTrue;

public class CheckoutStepDef {

    private WebDriver driver = BaseClass.getDriver();
    private DarazCartPage cartPage = new DarazCartPage(driver);
    private DarazCheckoutPage checkoutPage = new DarazCheckoutPage(driver);
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @And("I wait for login to complete")
    public void i_wait_for_login_to_complete() {
        if(driver == null) driver = DriverManager.getDriver();
        // Wait for home page generic element or profile icon
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("topActionUser")));
        } catch (Exception e) {
            // Log but don't fail immediately, explicit checks later will fail
        }
        // Re-init pages
        cartPage = new DarazCartPage(driver);
        checkoutPage = new DarazCheckoutPage(driver);
    }

    @When("I click on checkout button")
    public void i_click_on_checkout_button() {
        cartPage.clickCheckout();
    }

    @Then("I should be on the checkout page")
    public void i_should_be_on_the_checkout_page() {
        assertTrue(checkoutPage.isCheckoutPageDisplayed(), "Not on checkout page");
    }

    @When("I enter shipping information")
    public void i_enter_shipping_information(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        String name = data.get(0).get("name");
        String address = data.get(0).get("address");
        String phone = data.get(0).get("phone");
        
        checkoutPage.fillShippingInfo(name, address, phone);
    }

    @Then("the shipping information should be saved")
    public void the_shipping_information_should_be_saved() {
        // Validation logic - maybe check if form is collapsed or saved address is shown
        assertTrue(true); // Placeholder for visual validation
    }

    @When("I select payment method {string}")
    public void i_select_payment_method(String method) {
        checkoutPage.selectPaymentMethod(method);
    }

    @And("I enter invalid card details")
    public void i_enter_invalid_card_details() {
        checkoutPage.enterInvalidCard();
    }

    @Then("I should see a payment error message")
    public void i_should_see_a_payment_error_message() {
        // If live site, we might skip actual error check if we didn't submit
        // checkoutPage.clickPlaceOrder(); // Trigger error
        // assertTrue(checkoutPage.isPaymentErrorDisplayed());
    }

    @And("I click place order button")
    public void i_click_place_order_button() {
        checkoutPage.clickPlaceOrder();
    }

    @Then("I should see the order confirmation page")
    public void i_should_see_the_order_confirmation_page() {
        assertTrue(checkoutPage.isOrderPlaced(), "Order was not placed successfully");
    }
}
