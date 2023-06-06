package com.browserstack.stepdefs.backend;

import com.browserstack.backend.HttpRequest;
import com.browserstack.backend.RequestObjects.Employee;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

public class EmployeeSteps {
    private HttpRequest httpRequest = new HttpRequest("https://dummy.restapiexample.com/api/v1");

    private Response response;

    private Employee employee = new Employee();

    @When("User get the employee with id as {int}")
    public void getEmployeeWithId(int employeeId) {
        response = httpRequest.get("employee/"+employeeId);
    }

    @Then("id of the employee appears as {int}")
    public void employeePresentWithId(int employeeId) {
        Assert.assertEquals(response.jsonPath().getInt("data.id"),employeeId);
    }

    @When("User set the employee name as {string}")
    public void userSetTheEmployeeNameAs(String name) {
        employee.setEmployee_name(name);
    }

    @When("User set the employee salary as {string}")
    public void userSetTheEmployeeSalaryAs(String salary) {
        employee.setEmployee_salary(salary);
    }

    @When("User set the employee age as {string}")
    public void userSetTheEmployeeAgeAs(String age) {
        employee.setEmployee_age(age);
    }

    @Then("User validate that response status code is {int}")
    public void userValidateThatResponseStatusCodeIs(int statusCode) {
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    @When("User requests to create employee data")
    public void userRequestsToCreateEmployeeData() {
        response = httpRequest.post("create",employee);
    }
}
