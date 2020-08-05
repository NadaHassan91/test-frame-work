Feature: Customer manipulation

  Scenario: Add Customer
    Given Navigate the URL
    And Click on banking link
    And Click on bank manager login
    And Click on add customer
    And fill all the required fields Name "Nada" and "Hassan" and Code "123" and get the id from the alert
    And Click on customer tab
    Then Assert that the user details are same as entered in the form



  Scenario: Open Customer
     Given Click on Open Account
     And Select the customer entered
     And Select a random currency
     And Submit account and get the id from the alert
     And Click on customer tab
     And Assert that the Account Number retrieved from the alert is displayed in its cell for the created customer


  Scenario: Delete Customer
    Given Click on customer tab
    And Delete Customer added
    Then Assert that customer is deleted is "Nada"