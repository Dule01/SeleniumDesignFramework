
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file
  
  Background: 
  Given I landed on Ecommerce Page

  @tag2
  Scenario Outline: Positive test of submitting order
    Given I logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on the Confirmation Page

    Examples: 
      | name                   |   password     | productName |
      | duleselenium@gmail.com |   D.Simic1995! | ZARA COAT 3 |
