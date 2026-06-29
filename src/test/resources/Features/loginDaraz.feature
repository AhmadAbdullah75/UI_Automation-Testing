Feature: Daraz login functionality

  Scenario Outline: Successful login with valid credentials on Daraz
    Given user is on the Daraz login page
    When user enters valid credentials
      | username     | password    |
      | 03171292801  | daraz123@   |
    And user clicks on the Daraz login button
    Then user is navigated to the Daraz home page

  Scenario Outline: Unsuccessful login with invalid credentials on Daraz
    Given user is on the Daraz login page
    When user enters invalid credentials
      | username     | password    |
      | invalidUser  | wrongPass   |
    And user clicks on the Daraz login button
    Then an error message is displayed

  Scenario Outline: Unsuccessful login with empty fields on Daraz
    Given user is on the Daraz login page
    When user enters empty credentials
    And user clicks on the Daraz login button
    Then an error message is displayed

Scenario Outline: Unsuccessful login with missing username on Daraz
  Given user is on the Daraz login page
  When user enters credentials
    | username | password    |
    |          | daraz123@   |
  And user clicks on the Daraz login button
  Then an error message is displayed

Scenario Outline: Unsuccessful login with missing password on Daraz
  Given user is on the Daraz login page
  When user enters credentials
    | username     | password |
    | 03171292801  |          |
  And user clicks on the Daraz login button
  Then an error message is displayed

