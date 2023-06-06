package com.browserstack.stepdefs.backend;

import com.browserstack.backend.HttpRequest;
import com.browserstack.backend.RequestObjects.User;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class UserSteps {

    private HttpRequest httpRequest = new HttpRequest("https://reqres.in/api");

    private Response response;

    private User user = new User();
    @When("User set the user job as {string}")
    public void userSetTheUserJobAs(String job) {
        user.setJob(job);
    }

    @When("User set the user name as {string}")
    public void userSetTheUserNameAs(String name) {
        user.setName(name);
    }

    @When("User requests to create user data")
    public void userRequestsToCreateUserData() {
        response = httpRequest.post("users",user);
    }
}
