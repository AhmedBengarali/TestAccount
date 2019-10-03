Feature: searchh by filter
  @web
  Scenario: click on filter
    Given I navigate to the PGA webapp
    Given I am logged in as  "q270932"
    Given I navigate to MyPGA homepage
    When I click on filter button
    And I click in filter field sub product
    And I enter "model"
    And I enter return
    Then the search result shows three PGA-objects