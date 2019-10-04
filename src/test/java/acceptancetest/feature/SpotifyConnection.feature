Feature: Testing Spotify Account
  Scenario Outline: Test Type of the Account
    Given Go to Spotify Login Page
    When Enter "<Login>"
    And Enter "<password>"
    Then Go to Account setting
    And Check the Type of The Account
    Examples:
    |Login|password|
  |Michelle.Alderman1@yahoo.com|Andermi1|
  |michelledelarasse@gmail.com|Pivoine9|
  |michelleunger@gmx.de|mimilucy|
  |miguelarizmendi40@gmail.com|Google123|
  |mikel_dg@yahoo.ca|mdg111872|
  |milagritos010@gmail.com|march3727|
  |milenasofiadelia@gmail.com|38319343|
  |miltonnicolasgaliano@gmail.com|1162492121|
  |mimine_marion21@hotmail.fr|Canelle21|
  |minx-63@hotmail.com|Jamess1992|
   |mistyblu5@hotmail.com|perryperryx2|
  |mittfra2710@outlook.de|mama101163 |
  |mmaulding604@gmail.com|softball604|
  |mocanuvlad_ics@yahoo.com|folk550hit|
  |molohre@gmail.com|tomo1018|