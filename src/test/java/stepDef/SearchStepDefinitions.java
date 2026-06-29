package stepDef;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.DarazSearchPage;
import utils.BaseClass;
import utils.DriverManager;
import utils.ExcelReader;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class SearchStepDefinitions {

    private WebDriver driver = BaseClass.getDriver();
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    private DarazSearchPage searchPage;
    private String excelFilePath = "src/test/resources/testdata/testData.xlsx";

    @Given("I am on the homepage")
    public void i_am_on_the_homepage() {
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.get("https://www.daraz.pk/");
        searchPage = new DarazSearchPage(driver);
    }

    @When("I search for the term from Excel")
    public void i_search_for_the_term_from_excel() {
        String searchTerm = ExcelReader.getSearchTerm(excelFilePath, "Sheet1", 0, 0);

        searchPage.writeInSearchBox(searchTerm);
        searchPage.clickSearchButton();

        // Wait until search results are visible
        wait.until(ExpectedConditions.urlContains("search"));
    }

    @Then("I should see search results")
    public void i_should_see_search_results() {
        assertTrue(driver.getCurrentUrl().contains("search"), "Search results not displayed.");
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
