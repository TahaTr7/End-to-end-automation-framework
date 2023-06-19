Feature: Create new folder

  Scenario: Create new folder in EDAM
    Given User in HomePage
    When User click on create button
    And click any option from the list
      | OptionsList |
      | folder      |
    And user enters data in the form and save
      | OptionsList | Title                        | Name                         |
      | folder      | Test-Automation-CreateFolder | Test-Automation-CreateFolder |
    Then user delete selected folder or asset
      | OptionsList | Title                        |
      | folder      | Test-Automation-CreateFolder |

