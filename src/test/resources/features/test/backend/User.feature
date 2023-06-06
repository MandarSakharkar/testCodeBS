Feature: Employee Demo

  Scenario: Validate user creation
    When User set the user name as "mandar"
    When User set the user job as "leader"
    When User requests to create user data
    Then User validate that response status code is 201