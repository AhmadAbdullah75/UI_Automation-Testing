package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DarazLoginPage {
    private WebDriver driver;


    // Locators
    private By usernameField = By.xpath("//input[@placeholder='Please enter your Phone Number or Email']");
    private By passwordField = By.xpath("//input[@placeholder='Please enter your password']");
    private By loginButton = By.xpath("//button[contains(text(), 'LOGIN')]");

    // Constructor
    public DarazLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public void enterUsername(String username) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginBtn.click();
    }

    public boolean isUserLoggedIn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("daraz.pk"));
        return driver.getCurrentUrl().contains("daraz.pk");
    }
    public String getErrorMessage() {
        // Adjust the locator according to the actual error message element on the page
        try {
            return driver.findElement(By.cssSelector(".error-message-selector")).getText(); // Example CSS selector
        } catch (Exception e) {
            return ""; // Return empty string if no error message is found
        }
    }
}
