package com.ikea.homesmart.utils;

import io.cucumber.spring.ScenarioScope;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Component;

@Component
public class RestUtils {
    private RequestSpecification httpRequest;

    public RequestSpecification request() {
        RestAssured.useRelaxedHTTPSValidation();
        httpRequest = RestAssured.given();
        return httpRequest;
    }
}
