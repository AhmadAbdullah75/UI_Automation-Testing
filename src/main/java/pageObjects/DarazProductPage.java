package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DarazProductPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    // Note: These locators are best-guess based on standard Daraz layout.
    // Real implementation might need adjustment based on actual DOM.
    By addToCartButton = By.xpath("//button[contains(@class,'add-to-cart') or contains(text(),'Add to Cart')]");
    By buyNowButton = By.xpath("//span[contains(text(), 'Buy Now')]/parent::button");
    By productTitle = By.xpath("//h1[@class='pdp-mod-product-badge-title']");
    By successMessage = By.xpath("//div[contains(@class, 'pdp-cart-concern')]");

    public DarazProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    public void clickAddToCart() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        btn.click();
    }

    public boolean isProductPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void goToCart() {
        // Usually there's a popup or a top bar cart icon
        // Assuming "Go to Cart" button in popup or top icon
        By cartIcon = By.xpath("//a[contains(@href, '/cart')]"); // Generic cart link
        WebElement cartElement = wait.until(ExpectedConditions.presenceOfElementLocated(cartIcon));

        // Use JavascriptExecutor as element might be zero-sized or obscured
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", cartElement);
    }
}
