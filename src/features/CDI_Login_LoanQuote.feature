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
@SmokeTest @LoginTest
Feature: CDI login and Loan Quote
  I want to use this template for my feature file

  @postiveScenario
  Scenario: Test CDI Login scenario with valid credentials
    Given Open Chrome and lanuch application
    When I enter valid UN and PW
    Then User should be able to login successfully

  @negativeScenario
  Scenario: Test CDI Login scenario with invalid credentials
    Given Open Chrome and lanuch application
    When I enter invalid UN and PW
    Then Error message should throw
    And Close the browser

  @quoteScenario
  Scenario Outline: Create Loan Quote
    Given Open Chrome and lanuch application
    When I enter valid UN and PW
    And Create Loan Quote with valid policy "<policy>" and carrier "<carrier>"
    Then User should be able to save quote

    Examples: 
      | policy       | carrier |
      | 204187734USU | BLIC-U  |
      | 205011126USU | BLIC-U  |
