@CommonLogin_page
Feature: Login Page Verifications

  Scenario: Login to Edam Application
    Given User Navigate to Login Page
    And Enter User Credentials
    And User Clicks SignIn Button
    Then User Should See home page