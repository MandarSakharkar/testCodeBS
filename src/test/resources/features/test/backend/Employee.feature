Feature: Employee Demo

  Scenario: Validate employee details
    When User get the employee with id as 1
    Then id of the employee appears as 1

  Scenario: Validate employee user creation
    When User set the employee name as "mandar"
    When User set the employee salary as "1000"
    When User set the employee age as "26"
    When User requests to create employee data
    Then User validate that response status code is 201