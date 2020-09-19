@TEC-1020 @ui @db @regressiontest
Feature: Validating UI data with DB

    Scenario: Validating first four employees from UI data with DB
      Given User navigates to MyApp homepage
      When User gets all data from UI
      Then User validates UI data with DB data

