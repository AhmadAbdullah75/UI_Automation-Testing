Feature: Form Submission on Daraz

  @Form
  Scenario: Subscribe to newsletter with valid email
    Given I am on the homepage
    When I scroll to the footer
    And I enter "testuser@example.com" in the newsletter subscription box
    And I click subscribe button
    Then I should see a subscription success message

  @Form
  Scenario: Subscribe to newsletter with invalid email
    Given I am on the homepage
    When I scroll to the footer
    And I enter "invalid-email" in the newsletter subscription box
    And I click subscribe button
    Then I should see an invalid email error message
