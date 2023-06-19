Feature: Download Rendition

  Scenario: Verify Rendition is displaying in asset properties
    Given User in HomePage
    When User click on brand and user click on asset
    And User click on left dropdown and select renditions
    Then User should be able to see renditions
