Feature: releases

  Background:
    Given I login in as a user

  Scenario: new release
    Given the create releases page
    When I input a release for tomorrow
    And I click submit
    Then I do not see an error
    And I see my release

  Scenario: new release with bad date
    Given the create releases page
    When I input a release for badger
    And I click submit
    Then I see an error

  Scenario: new release can be updated
    Given a new release
    When I open the edit release page
    And I set when to wednesday
    And I click submit
    Then I do not see an error
    And I see my release

  Scenario: remove a release
    Given a new release
    When I open the releases page
    And I click my release's remove button
    Then I do not see an error
    And I am on the releases page
    And I do not see my release