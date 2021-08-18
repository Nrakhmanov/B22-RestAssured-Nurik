package com.cybertek.day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;



public class ORDSApiTestWithParameters {

    @BeforeAll
    public static void init() {
        baseURI = "http://3.92.136.109:1000/ords/hr";
    }

      /*
        Given accept type is Json
        And parameters: q = {"region_id":2}
        When users sends a GET request to "/countries"
        Then status code is 200
        And Content type is application/json
        And Payload should contain "United States of America"
     */

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .queryParams("q", "{\"region_id\":2}")
                .log().all()
                .when().get("/countries");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.header("Content-Type"));
        assertTrue(response.body().asString().contains("United States of America"));

        response.prettyPrint();

    }

    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .queryParams("q", "{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees");


        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        assertTrue(response.body().asString().contains("IT_PROG"));

        response.prettyPrint();
    }



}
