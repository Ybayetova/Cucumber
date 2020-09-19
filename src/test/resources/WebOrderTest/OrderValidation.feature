Feature: Editing existing order

  Scenario: Validating that existing order can be edited
    Given User with an existing order navigates to View all orders Tab
    When User clicks on Edit icon next to his/her order
    Then User validates that his/her order can be edited


  Scenario: Validating that existing order can not be edited
    Given User with an existing order navigates to View all orders
    When User clicks on Edit Icon next to his/her order
    Then User validates that his/her order can not be edited


  Scenario: Validating that existing order is edited
    Given User navigates to Edit Order Page
    When User fills all mandatory fields and updates the order
    Then User is able to click on Update button and save changes

  Scenario: Validating that existing order is not edited
    Given User  navigates to Edit Order Page
    When User doesn't fill all mandatory fields
    Then User is not able to click on Update button