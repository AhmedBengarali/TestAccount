Feature: Create PGA Objects
  @web
  Scenario Outline: Create 10 PGA Objects
    Given I navigate to the PGA webapp
    Given I am logged in as  "q270932"
    Given I navigate to MyPGA homepage
    When I click on Create PGA button
    And I enter PGA name as "<PGA-name>"
    And I enter Scope name as "<scope-name>"
    And I select Product as "<product>"
    And I select Subproduct as "<subproduct>"
    And I select Application as "<application>"
    And I click on Create PGA button
    Then new PGA-object is created
    And overview page of new created PGA object is displayed
    Examples:
      |PGA-name|scope-name|product|subproduct|application|
      |Initialized PGA-Testobject 1|Initialized Test-Scope 1|SW-19 QA||BRIO|

      |Initialized PGA-Testobject 2|Initialized Test-Scope 1|SWP-2086 Integration |Identification & Positioning||

#      |Initialized PGA-Testobject 3|Initialized Test-Scope 1|SW Delivery Pipeline |0|BRI Online (BRIO) - Betriebsreifeindex|

#      |Initialized PGA-Testobject 4|Initialized Test-Scope 2|SWP-2086 Integration| Identification & Positioning|0|

 #     |Initialized PGA-Testobject 5|Initialized Test-Scope 2|Agile Working Model (AWM)|Audits, eDiscovery, Investigations|0|

 #     |Initialized PGA-Testobject 6|Initialized Test-Scope 2|Agile Working Model (AWM)|License Compliance Mgmt|0|

  #    |Initialized PGA-Testobject 7|Initialized Test-Scope 3|Shopfloor Management |0|Digital Shopfloor Management Platform|

   #   |Initialized PGA-Testobject 8|Initialized Test-Scope 3|Shopfloor Management |0|Pilot Digitale Prozesstafel|
    #  |Initialized PGA-Testobject 9|Initialized Test-Scope 4|Blockchain Technology |0|Blockchain Technology|

     # |Initialized PGA-Testobject 10|Initialized Test-Scope 5|Billing & Catalog |Billing|0|