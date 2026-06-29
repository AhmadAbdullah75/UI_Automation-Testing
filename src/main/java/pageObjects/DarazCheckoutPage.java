package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DarazCheckoutPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By shippingName = By.xpath("//input[@placeholder='Enter your full name']");
    By shippingAddress = By.xpath("//input[@placeholder='Ex: House# 123, Street# 123, ABC Road']"); 
    By shippingPhone = By.xpath("//input[@placeholder='Enter your phone number']");
    By saveButton = By.xpath("//button[contains(text(), 'SAVE')]");
    
    // Payment Locators
    By placeOrderButton = By.xpath("//button[contains(text(), 'Place Order')]");
    By paymentError = By.xpath("//div[contains(@class, 'error-msg')]");
    // Order success
    By orderSuccessMsg = By.xpath("//div[contains(text(), 'Order Success')]"); // Placeholder

    public DarazCheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isCheckoutPageDisplayed() {
        return driver.getCurrentUrl().contains("checkout");
    }

    public void fillShippingInfo(String name, String address, String phone) {
        // Need to check if fields are present or if it's a saved address
        // Assuming new address form for simplicity or editing existing
        try {
           wait.until(ExpectedConditions.visibilityOfElementLocated(shippingName)).sendKeys(name);
           driver.findElement(shippingAddress).sendKeys(address);
           driver.findElement(shippingPhone).sendKeys(phone);
           driver.findElement(saveButton).click();
        } catch (Exception e) {
            System.out.println("Shipping info form might be hidden or already filled.");
        }
    }

    public void selectPaymentMethod(String method) {
        // e.g., click on generic payment method container containing text
        By methodLocator = By.xpath("//div[contains(@class, 'payment-method') and contains(text(), '" + method + "')]");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(methodLocator)).click();
        } catch (Exception e) {
            System.out.println("Payment method " + method + " not found or selectable.");
        }
    }

    public void enterInvalidCard() {
         // Placeholder for card inputs
    }

    public boolean isPaymentErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(paymentError)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickPlaceOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton)).click();
    }
    
    public boolean isOrderPlaced() {
         try {
            wait.until(ExpectedConditions.urlContains("success"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
