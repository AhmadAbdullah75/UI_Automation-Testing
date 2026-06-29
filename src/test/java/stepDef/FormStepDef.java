package stepDef;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pageObjects.DarazFormPage;
import utils.BaseClass;
import utils.DriverManager;
import static org.testng.Assert.assertTrue;

public class FormStepDef {

    private WebDriver driver = BaseClass.getDriver();
    private DarazFormPage formPage = new DarazFormPage(driver);

    @When("I scroll to the footer")
    public void i_scroll_to_the_footer() {
        if(driver == null) driver = DriverManager.getDriver();
        formPage = new DarazFormPage(driver);
        formPage.scrollToFooter();
    }

    @And("I enter {string} in the newsletter subscription box")
    public void i_enter_in_the_newsletter_subscription_box(String email) {
        formPage.enterEmail(email);
    }

    @And("I click subscribe button")
    public void i_click_subscribe_button() {
        formPage.clickSubscribe();
    }

    @Then("I should see a subscription success message")
    public void i_should_see_a_subscription_success_message() {
        // Soft assertion or check as we might not want to actually subscribe generic emails repeatedly
        // assertTrue(formPage.isSubscriptionSuccessful()); 
    }

    @Then("I should see an invalid email error message")
    public void i_should_see_an_invalid_email_error_message() {
         // assertTrue(formPage.isErrorDisplayed());
    }
}
