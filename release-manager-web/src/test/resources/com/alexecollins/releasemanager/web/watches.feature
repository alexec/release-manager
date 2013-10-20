Feature: watches

  Background:
    Given I login in as a user

  Scenario: create a release
    Given I add a watch to /releases.html
    When I create a new release
    Then I get an email
