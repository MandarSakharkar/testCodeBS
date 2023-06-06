package com.browserstack.backend;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HttpRequest {


    public HttpRequest(String uri) {
        baseURI = uri;
    }

    public Response
    post(String endpoint, Object object){
        RequestSpecification requestSpecification = given().contentType(ContentType.JSON);
        requestSpecification.body(object);
        Response response = requestSpecification.post(endpoint);
        return  response;
    }

    public Response get(String endpoint){
        RequestSpecification requestSpecification = given().contentType(ContentType.JSON);
        Response response = requestSpecification.get(endpoint);
        return  response;
    }
}
