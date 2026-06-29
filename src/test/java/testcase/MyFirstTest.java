package testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MyFirstTest {
    public static void main(String[] args) {
        // Setting up the ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();

        // Creating an instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Opening a website (example: Zoho)
        driver.get("https://www.zoho.com");
        driver.findElement(By.linkText("Sign In")).click();
        driver.findElement(By.id("login_id")).sendKeys("ayeshanaseer19999@gmail.com");

        driver.findElement(By.xpath("//span[normalize-space()='Next']")).click(); 
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("ayesha19999@");
        driver.findElement(By.xpath("//button[@id='nextbtn']//span[contains(text(),'Sign in')]")).click();
        
        // Printing the title of the page
        System.out.println("Page title is: " + driver.getTitle());

        // Closing the browser
        driver.quit();
    }
}
