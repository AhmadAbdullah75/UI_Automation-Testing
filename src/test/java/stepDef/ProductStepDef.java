package stepDef;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pageObjects.DarazCartPage;
import pageObjects.DarazProductPage;
import pageObjects.DarazSearchPage;
import utils.BaseClass;
import utils.DriverManager;

import static org.testng.Assert.assertTrue;

public class ProductStepDef {

    private WebDriver driver = BaseClass.getDriver();
    private DarazSearchPage searchPage = new DarazSearchPage(driver);
    private DarazProductPage productPage = new DarazProductPage(driver);
    private DarazCartPage cartPage = new DarazCartPage(driver);

    // Reuse "I search for..." from SearchStepDefinitions if possible, but here we have direct strings in feature
    // So we implement generic search step
    @When("I search for {string}")
    public void i_search_for(String product) {
        if(driver == null) driver = DriverManager.getDriver(); // Safety check
        // Re-init pages if driver changed (e.g. restart)
        searchPage = new DarazSearchPage(driver);
        productPage = new DarazProductPage(driver);
        cartPage = new DarazCartPage(driver);
        
        searchPage.writeInSearchBox(product);
        searchPage.clickSearchButton();
    }

    @When("I click on the first product in results")
    public void i_click_on_the_first_product_in_results() {
        searchPage.clickFirstProduct();
    }

    @Then("I should see the product details page")
    public void i_should_see_the_product_details_page() {
        assertTrue(productPage.isProductPageDisplayed(), "Product page is not displayed");
    }

    @When("I add the product to the cart")
    public void i_add_the_product_to_the_cart() {
        productPage.clickAddToCart();
        // Wait for success or notification
        try { Thread.sleep(2000); } catch (InterruptedException e) {} 
    }

    @Then("the product should be in the cart")
    public void the_product_should_be_in_the_cart() {
        productPage.goToCart(); // Navigate to cart to verify
        assertTrue(cartPage.isProductInCart(), "Cart is empty but should have products");
    }

    @When("I go to the cart page")
    public void i_go_to_the_cart_page() {
        productPage.goToCart();
    }

    @When("I remove the product from the cart")
    public void i_remove_the_product_from_the_cart() {
        cartPage.removeProduct();
    }

    @Then("the cart should be empty")
    public void the_cart_should_be_empty() {
        assertTrue(cartPage.isCartEmpty(), "Cart is not empty");
    }
}
