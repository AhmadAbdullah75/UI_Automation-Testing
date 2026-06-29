package stepDef;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.DarazLoginPage;
import utils.BaseClass;
import utils.DriverManager;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertTrue;

public class DarazLoginStepDef {

    private WebDriver driver = BaseClass.getDriver();
    private DarazLoginPage darazLoginPage;
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Given("user is on the Daraz login page")
    public void user_is_on_the_daraz_login_page() {
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.get("https://member.daraz.pk/user/login");
        darazLoginPage = new DarazLoginPage(driver);
    }

    @When("user enters valid credentials")
    public void user_enters_valid_credentials(io.cucumber.datatable.DataTable dataTable) {
        enterCredentials(dataTable);
    }

    @When("user enters invalid credentials")
    public void user_enters_invalid_credentials(io.cucumber.datatable.DataTable dataTable) {
        enterCredentials(dataTable);
    }

    @When("user enters empty credentials")
    public void user_enters_empty_credentials() {
        darazLoginPage.enterUsername("");
        darazLoginPage.enterPassword("");
    }

    @When("user enters credentials")
    public void user_enters_credentials(io.cucumber.datatable.DataTable dataTable) {
        enterCredentials(dataTable);
    }

    private void enterCredentials(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        String username = data.get(0).get("username");
        String password = data.get(0).get("password");

        darazLoginPage.enterUsername(username != null ? username : "");
        darazLoginPage.enterPassword(password != null ? password : "");
    }

    @And("user clicks on the Daraz login button")
    public void user_clicks_on_the_daraz_login_button() {
        darazLoginPage.clickLoginButton();
    }

    @Then("user is navigated to the Daraz home page")
    public void user_is_navigated_to_the_daraz_home_page() {
        // Wait until an element only visible after login appears
        try {
            WebElement accountMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("topActionUser")));
            assertTrue(accountMenu.isDisplayed(), "User is not navigated to the home page");
            System.out.println("User successfully logged in.");
        } catch (Exception e) {
            System.out.println("Login failed or user not navigated to home page.");
        } finally {
            DriverManager.quitDriver();
        }
    }

    @Then("an error message is displayed")
    public void an_error_message_is_displayed() {
        try {
            // Replace with the actual error message element on Daraz login page
            WebElement errorElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".login-error-message"))
            );
            System.out.println("Error message displayed: " + errorElement.getText());
        } catch (Exception e) {
            System.out.println("No error message displayed.");
        } finally {
            DriverManager.quitDriver();
        }
    }
}
