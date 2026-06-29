package utils;

import org.openqa.selenium.WebDriver;

public class BaseClass {

    // Make driver protected so step definitions can use it
    protected static WebDriver driver;

    // Initialize WebDriver
    public static void initialize() {
        driver = DriverManager.getDriver();
    }

    // Quit WebDriver
    public static void tearDown() {
        DriverManager.quitDriver();
    }

    // Getter for step definitions
    public static WebDriver getDriver() {
        return driver;
    }
}
