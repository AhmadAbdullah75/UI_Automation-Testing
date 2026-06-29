package stepDef;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseClass;
import utils.DriverManager;

import java.time.Duration;

public class LanguageStepDefinitions {

    private WebDriver driver = BaseClass.getDriver();
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Given("I am on the Daraz homepage")
    public void i_am_on_the_daraz_homepage() {
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.get("https://www.daraz.pk/");
    }

    @When("I click on the language change button")
    public void i_click_on_the_language_change_button() {
        WebElement languageButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("topActionSwitchLang"))
        );
        languageButton.click();
    }

    @When("I select Urdu as the language")
    public void i_select_urdu_as_the_language() {
        WebElement urduOption = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='lzd-switch-item ' and @data-lang='ur']"))
        );
        urduOption.click();
    }

    @Then("I return to the homepage")
    public void i_return_to_the_homepage() {
        driver.get("https://www.daraz.pk/");
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
