Feature: Search functionality on Daraz website

  Scenario: Searching for an item from Excel
    Given I am on the homepage
    When I search for the term from Excel
    Then I should see search results
