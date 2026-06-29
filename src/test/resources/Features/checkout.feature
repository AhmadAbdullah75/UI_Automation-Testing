Feature: Checkout Process on Daraz

  Background:
    Given user is on the Daraz login page
    When user enters valid credentials
      | username     | password    |
      | 03171292801  | daraz123@   |
    And user clicks on the Daraz login button
    And I wait for login to complete

  @Checkout
  Scenario: Proceed to checkout from cart
    Given I search for "Watch"
    And I click on the first product in results
    And I add the product to the cart
    And I go to the cart page
    When I click on checkout button
    Then I should be on the checkout page

  @Checkout
  Scenario: Fill checkout information
    Given I search for "Pen"
    And I click on the first product in results
    And I add the product to the cart
    And I go to the cart page
    And I click on checkout button
    When I enter shipping information
      | name | address | city | phone |
      | Test User | 123 Test St | Karachi | 03001234567 |
    Then the shipping information should be saved

  @Checkout
  Scenario: Enter invalid payment data
    Given I search for "Notebook"
    And I click on the first product in results
    And I add the product to the cart
    And I go to the cart page
    And I click on checkout button
    When I select payment method "Credit Card"
    And I enter invalid card details
    Then I should see a payment error message

  @Checkout
  Scenario: Successful order placement (Simulation)
    Given I search for "Sticker"
    And I click on the first product in results
    And I add the product to the cart
    And I go to the cart page
    And I click on checkout button
    When I select payment method "Cash On Delivery"
    And I click place order button
    Then I should see the order confirmation page
