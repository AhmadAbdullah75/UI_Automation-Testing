package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class DarazCartPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By cartItems = By.xpath("//div[contains(@class, 'cart-item')]");
    By removeButton = By.xpath("//span[contains(text(), 'DELETE')]"); // Often 'DELETE' or trash icon
    By checkoutButton = By.xpath("//button[contains(text(), 'PROCEED TO CHECKOUT')]");
    By emptyCartMessage = By.xpath("//div[contains(text(), 'There are no items in this cart')]");

    public DarazCartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isProductInCart() {
        try {
            List<WebElement> items = driver.findElements(cartItems);
            return items.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public void removeProduct() {
        // Click remove on the first item
        WebElement removeBtn = wait.until(ExpectedConditions.elementToBeClickable(removeButton));
        removeBtn.click();
        
        // Confirm removal if there's a popup (Daraz often has a confirm modal)
        // By confirmRemove = By.xpath("//button[contains(text(), 'REMOVE')]");
        // if(driver.findElements(confirmRemove).size() > 0) { driver.findElement(confirmRemove).click(); }
        // For simplicity, assuming direct remove or handling generic alert:
        try {
             By confirmRemove = By.xpath("//button[contains(text(), 'REMOVE')]");
             wait.until(ExpectedConditions.elementToBeClickable(confirmRemove)).click();
        } catch (Exception e) {
            // Ignore if no popup
        }
    }

    public boolean isCartEmpty() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartMessage));
            return true;
        } catch (Exception e) {
            // Alternative: check if no cart items exist
            return driver.findElements(cartItems).isEmpty();
        }
    }

    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }
}
