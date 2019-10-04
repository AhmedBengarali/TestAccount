Feature: Testing Spotify Account
  Scenario Outline: Test Type of the Account
    Given Go to Spotify Login Page
    When Enter "<Login>"
    And Enter "<password>" and Submit
    Then Go to Account setting
    And Check the Type of The Account
    Examples:
    |Login|password|
  |Michelle.Alderman1@yahoo.com|Andermi1|
