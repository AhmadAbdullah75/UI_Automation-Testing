Feature: Language Change Functionality on Daraz

  Scenario: Successfully change language to Urdu
    Given I am on the Daraz homepage
    When I click on the language change button
    And I select Urdu as the language
    Then I return to the homepage
