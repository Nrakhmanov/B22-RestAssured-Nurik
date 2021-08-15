package com.cybertek.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpartanGetRequest {

    String baseUrl = "http://3.92.136.109:8000";

    //Given Accept type application/json
    //When user Get request to api/spartans end point
    //Then Status code must be 200
    //Then response Content Type must be application/json
    //And response body should include spartan result

    @Test
    public void test1() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl + "/api/spartans");


        //printing status code from response object
        System.out.println("response.statusCode() = " + response.statusCode());

        //printing response content from response object
        System.out.println("response.contentType() = " + response.contentType());

        //printing whole result body
        System.out.println("response.prettyPrint() = " + response.prettyPrint());


        //how to do API testing
        //verify status code is 200
        Assertions.assertEquals(response.statusCode(), 200);

        //verify Content type is application/json
        Assertions.assertEquals(response.contentType(), "application/json");

    }
}