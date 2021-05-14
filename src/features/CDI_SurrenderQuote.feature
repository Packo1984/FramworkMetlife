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
Feature: Surrender Quote
  I want to use this template for my feature file

  @complexScenario
  Scenario Outline: Validate quote values & create quote
    Given User login to application
    When Create Quote "<quote>" with valid policy "<policy>" and carrier "<carrier>" 
    And Policy gets company name "<company_name>" from wall
    And Policy should be active
    Then Validate full amount available "<fullamount_available>" and surrender penalty "<surrender_penalty>"
    And Validate cost basis "<costbasis>" and override restrictive notes "<notes>"
    And User should be able to save quote

    Examples: 
      | policy       | carrier | notes								| quote									| fullamount_available	| surrender_penalty	| costbasis		|	company_name					|
      | 205011126USU | BLIC-U  | MANUAL RESTRAINTS		|Surrender Quote - Full	| 7848.26								|	1181.88						|	17194.80		|	Brighthouse Financial	|
     # | 205011126USU | BLIC-U  | MANUAL RESTRAINTS		|Loan	Quote							| 7802.65								| 1181.88						|	0.00				|	Brighthouse Financial	|