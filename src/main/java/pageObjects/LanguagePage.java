package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LanguagePage {
    private WebDriver driver;

    // Locators
    private By languageButton = By.xpath("//span[contains(text(), 'زبان تبدیل کریں')]"); // Updated locator
    private By urduLanguageOption = By.xpath("//option[contains(text(),'اردو')]"); // Update with actual locator for Urdu option

    // Constructor
    public LanguagePage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void navigateToHomepage(String url) {
        driver.get(url);
    }

    public void clickLanguageButton() {
        driver.findElement(languageButton).click();
    }

    public void selectUrduLanguage() {
        driver.findElement(urduLanguageOption).click();
    }

    public String getCurrentLanguage() {
        // Logic to get the current language displayed on the page
        return driver.findElement(By.id("current-language")).getText(); // Update with actual locator
    }
}
