Feature: Create PGA
@REST
Scenario: REST-assured: Create PGA
Given I use the REST API
And I use user "q270932" and password "q270932"
And I select PGA-name "Test-PGA-name"
And I select PGA-scope "Test-PGA-scope"
And I select product ID "SWP-120" and product name "Agile Working Model (AWM) 1.0"
And I create a new PGA