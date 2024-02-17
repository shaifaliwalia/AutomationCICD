@tag
Feature: Purchase the order from Ecommerce Website

 Background:
	Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Title of your scenario outline
    Given Logged in application with email <email> and password <password>
    When Add item to cart with productname <productname>
    And checkout <productname> and submit the order
    Then "THANKYOU FOR THE ORDER." verify the message on ConfirmatiomPage

    Examples: 
      |             email         |   password    | productname  |
      | walia.shaifali8@gmail.com |   Shaifali@12 | ZARA COAT 3  |
      
