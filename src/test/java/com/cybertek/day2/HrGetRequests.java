package com.cybertek.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class HrGetRequests {


    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://3.92.136.109:1000/ords/hr";
    }


    @DisplayName("Get request to /regions")
    @Test
    public void test1() {

        Response response = get("/regions");

        System.out.println(response.statusCode());


    }

     /*
        Given accept type is application/json
        When user sends get request to /regions/2
        Then response status code must be 200
        and content type equals to application/json
        and response body contains Americas
     */

    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .when().get("/regions/2");


        System.out.println(response.statusCode());

        assertEquals(200, response.statusCode());

        assertEquals("application/json", response.contentType());

        response.prettyPrint();

        assertTrue(response.body().asString().contains("Americas"));


    }

}
