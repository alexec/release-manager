Feature: security

  Background:
    Given logged out

  Scenario: login as user
    When I login as a user
    Then I can access releases.html
    But I cannot access components.html

  Scenario: login as admin
    When I login as a admin
    Then I can access releases.html
    And I can access components.html

  Scenario: logout
    When I login as a user
    Then I logout
    Then I cannot access releases.html
    And I cannot access components.html

  Scenario: sign-off
    When I login as a user
    Then I cannot access releases/*/sign-off/1.html
