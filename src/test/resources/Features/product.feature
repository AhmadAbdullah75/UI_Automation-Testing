Feature: Product Interaction on Daraz

  @Product
  Scenario: Search for a specific product
    Given I am on the homepage
    When I search for "Mobile Phone"
    Then I should see search results

  @Product
  Scenario: Open product details page
    Given I am on the homepage
    When I search for "Laptop"
    And I click on the first product in results
    Then I should see the product details page

  @Product
  Scenario: Add product to cart
    Given I am on the homepage
    When I search for "Headphones"
    And I click on the first product in results
    And I add the product to the cart
    Then the product should be in the cart

  @Product
  Scenario: Remove product from cart
    Given I am on the homepage
    When I search for "Mouse"
    And I click on the first product in results
    And I add the product to the cart
    And I go to the cart page
    And I remove the product from the cart
    Then the cart should be empty
