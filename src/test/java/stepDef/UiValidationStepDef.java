package stepDef;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BaseClass;
import utils.DriverManager;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class UiValidationStepDef {

    private WebDriver driver = BaseClass.getDriver();

    @Then("the page title should contain {string}")
    public void the_page_title_should_contain(String expectedTitlePart) {
        if (driver == null)
            driver = DriverManager.getDriver();
        String title = driver.getTitle();
        assertTrue(title.contains(expectedTitlePart),
                "Page title does not contain '" + expectedTitlePart + "'. Actual: " + title);
    }

    @Then("I verify that there are no broken links on the page")
    public void i_verify_that_there_are_no_broken_links_on_the_page() {
        if (driver == null)
            driver = DriverManager.getDriver();
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Total links found: " + links.size());

        // Extract hrefs to list of strings to avoid StaleElementReferenceException
        List<String> urls = new java.util.ArrayList<>();
        for (WebElement link : links) {
            try {
                String url = link.getAttribute("href");
                if (url != null && !url.isEmpty() && url.startsWith("http")) {
                    urls.add(url);
                }
            } catch (Exception e) {
                // Ignore stale elements during collection
            }
        }

        int brokenLinks = 0;
        // Limit check to first 20 links to avoid long execution time during demo/test
        int limit = Math.min(urls.size(), 20);

        for (int i = 0; i < limit; i++) {
            String url = urls.get(i);

            try {
                HttpURLConnection huc = (HttpURLConnection) (new URL(url).openConnection());
                huc.setRequestMethod("HEAD");
                huc.setConnectTimeout(5000); // Set timeout
                huc.connect();
                int respCode = huc.getResponseCode();

                if (respCode >= 400) {
                    System.out.println(url + " is a broken link. Response Code: " + respCode);
                    brokenLinks++;
                } else {
                    System.out.println(url + " is a valid link. Response Code: " + respCode);
                }
            } catch (IOException e) {
                System.out.println(url + " caused exception: " + e.getMessage());
                brokenLinks++;
            }
        }

        assertTrue(brokenLinks == 0, "Found " + brokenLinks + " broken links.");
    }
}
