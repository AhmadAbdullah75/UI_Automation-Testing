package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DarazFormPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators for Newsletter in footer
    By newsletterInput = By.xpath("//input[@type='email']");
    By subscribeButton = By.xpath("//button[contains(text(), 'SUBSCRIBE')]"); // Often generic
    By successMessage = By.xpath("//div[contains(text(), 'You have successfully subscribed')]"); // Placeholder
    By errorMessage = By.xpath("//div[contains(text(), 'Invalid email')]"); // Placeholder

    public DarazFormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    public void scrollToFooter() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void enterEmail(String email) {
        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(newsletterInput));

        // Ensure scroll to element
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", input);

        // Wait for visibility after scroll
        wait.until(ExpectedConditions.visibilityOf(input));

        input.clear();
        input.sendKeys(email);
    }

    public void clickSubscribe() {
        driver.findElement(subscribeButton).click();
    }

    public boolean isSubscriptionSuccessful() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
