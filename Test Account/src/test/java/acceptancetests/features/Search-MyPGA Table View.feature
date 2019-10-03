Feature: Search-MyPGA Table View
    @web
    Scenario: Search-MyPGA Table View (return)
      Given I navigate to the PGA webapp
      Given I am logged in as  "q270932"
      Given I navigate to MyPGA homepage
      When I click into search field
      And I enter "Test-PGA-name"
      And I enter return
      Then the search result shows one PGA-object


