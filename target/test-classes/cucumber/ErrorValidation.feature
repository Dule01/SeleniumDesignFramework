@tag
Feature: Error validation
  I want to use this template for my feature file

  @Negative
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce Page
    When I logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name                   |   password     |
      | duleselenium@gmail.com |   D.Simic1995  |
