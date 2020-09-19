@regressiontest @TEC-1002
Feature: Order Creation Functionality

   Background: Common steps for all scenarios
      Given User navigates to WebOrders application
      When User provides username "Tester" and password "test"
      Then User validates that application "is" logged in
      When User clicks on Order Part

   @TEC-2003 @ui
  Scenario: Creating order with valid data
     And User adds new order with data
     |Quantity|Customer name|Street     |City       |Zip  |State|Card Num|Exp Date|
     |2       |John Doe     | 123 Doe St|Des Plaines|10006| IL  |1234567 | 12/21  |
     #One value of list
     Then User clicks on Process button and validates "New order has been successfully added." message
     When User clicks View All Orders part
     Then User created order is added to list with data
        |Quantity|Customer name|Street     |City       |Zip  |State|Card Num|Exp Date|
        |2       |John Doe     | 123 Doe St|Des Plaines|10006| IL  |1234567 | 12/21  |


     @TEC-2015 @ui
      Scenario: Creating multiple order
        And User creates all orders from "TestData" excel file
        Then User clicks on Process button and validates "New order has been successfully added." message
        When User clicks View All Orders part
        Then User validates that orders from "TestData" excel file are created
