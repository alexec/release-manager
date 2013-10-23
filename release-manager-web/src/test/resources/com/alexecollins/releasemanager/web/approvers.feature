Feature: Approvers

  Background:
    Given I login as a admin

  Scenario: create new approver
    Given the create approver page
    And I input a approver
    And I click submit
    Then I do not see an error
    And I see my new approver
