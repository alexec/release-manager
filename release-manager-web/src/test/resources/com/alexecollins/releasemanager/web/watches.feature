Feature: watches

  Background:
    Given I login in as a user

  Scenario: add a watch
    When I create a new release
    Given I add a watch to releases
    Then I get an email