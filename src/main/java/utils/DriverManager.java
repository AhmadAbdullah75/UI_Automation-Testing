package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {

    private static WebDriver driver;

    // Initialize and return WebDriver
    public static WebDriver getDriver() {
        if (driver == null) {
            // Automatically setup ChromeDriver
            WebDriverManager.chromedriver().setup();

            // Chrome options
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");          // Maximize window
            options.addArguments("--incognito");                // Incognito mode
            options.addArguments("--disable-notifications");    // Disable popups

            driver = new ChromeDriver(options);
        }
        return driver;
    }

    // Quit WebDriver safely
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
