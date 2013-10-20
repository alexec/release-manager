Feature: security

  Scenario: login as user
    Given logged out
    When I login in as a user
    Then I can access releases.html
    But I cannot access components.html

  Scenario: login as admin
    Given logged out
    When I login in as a admin
    Then I can access releases.html
    And I can access components.html

  Scenario: logout
    Given logged out
    When I login in as a user
    Then I logout
    Then I cannot access releases.html
    And I cannot access components.html