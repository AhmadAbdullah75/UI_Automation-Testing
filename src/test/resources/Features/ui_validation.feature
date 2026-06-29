Feature: UI Validation on Daraz

  @UI
  Scenario: Validate Page Titles
    Given I am on the homepage
    Then the page title should contain "Daraz.pk"
    When I search for "Mobile"
    Then the page title should contain "Mobile"

  @UI
  Scenario: Detect Broken Links on Homepage
    Given I am on the homepage
    Then I verify that there are no broken links on the page
