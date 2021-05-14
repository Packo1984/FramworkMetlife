#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@SmokeTest @MobileLoginTest
Feature: login to amazon app and create user
  I want to use this template for my feature file

  @mobileScenario
  Scenario: Test Amazon App login with valid credentials
    Given Open Mobiledevice and launch application
    When I verify home page 
    And User clicks prime page
    Then User enter user details and click submit
    And Validate error message
     