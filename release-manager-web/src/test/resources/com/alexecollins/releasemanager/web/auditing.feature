Feature: auditing

  Background:
    Given I login as a admin

  Scenario: create approver
    Given I create a new approver
    When I look at the audit history
    Then I see the newest audit record is "admin" "created approver.*"

  Scenario: delete approver
    Given I create a new approver
    And I delete that approver
    When I look at the audit history
    Then I see the newest audit record is "admin" "deleted approver.*"

  Scenario: create release
    Given I create a new release
    When I look at the audit history
    Then I see the newest audit record is "admin" "created release .*"

  Scenario: delete release
    Given I create a new release
    And I delete that release
    When I look at the audit history
    Then I see the newest audit record is "admin" "deleted release .*"

  Scenario: add component
    Given I create a new release
    And I go to edit my new release
    And I add a component to the release
    When I look at the audit history
    Then I see the newest audit record is "admin" "added component .* to .*"

  Scenario: remove component
    Given I create a new release
    And I go to edit my new release
    And I add a component to the release
    And I remove a component from the release
    When I look at the audit history
    Then I see the newest audit record is "admin" "removed component .*"

  Scenario: add sign-off
    Given I create a new release
    And I go to edit my new release
    And I add a sign-off to the release
    When I look at the audit history
    Then I see the newest audit record is "admin" "added sign-off .* to .*"

  Scenario: remove sign-off
    Given I create a new release
    And I go to edit my new release
    And I add a sign-off to the release
    And I remove a sign-off to the release
    When I look at the audit history
    Then I see the newest audit record is "admin" "removed sign-off .*"

  Scenario: update sign-off
    Given I create a new release
    And I go to edit my new release
    And I add a sign-off to the release
    And I update a sign-off to the release to "AUTHORISED"
    When I look at the audit history
    Then I see the newest audit record is "admin" "updated sign-off .*"
