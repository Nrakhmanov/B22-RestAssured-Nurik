package com.cybertek.day2;

import com.sun.org.apache.xpath.internal.operations.And;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import javafx.beans.binding.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sun.net.www.content.text.plain;
import sun.security.util.Length;

import java.sql.SQLOutput;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanGetRequest {

    String baseUrl = "http://3.92.136.109:8000";

    //Given Accept type application/json
    //When user Get request to api/spartans end point
    //Then Status code must be 200
    //Then response Content Type must be application/json
    //And response body should include spartan result

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
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
        assertEquals(200, response.statusCode());

        //verify Content type is application/json
        assertEquals("application/json", response.contentType());

    }

    //Given Accept type application/json
    //When user Get request to api/spartans/3
    //Then Status code must be 200
    //Then response Content Type must be application/json
    //And json body should contain Fidole

    @DisplayName("GET one spartan api/spartans/3 and verify")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON).
                when().get(baseUrl + "/api/spartans/3");

        assertEquals(200, response.statusCode());

        assertEquals("application/json", response.contentType());

        Assertions.assertTrue(response.body().asString().contains("Fidole"));
    }

//    Given no headers provided
//    When Users sends GET request to /api/hello
//    Then response status code should be 200
//    And Content type header should be “text/plain;charset=UTF-8”
//    And header should contain date
//    And Content-Length should be 17
//    And body should be “Hello from Sparta"

    @DisplayName("Get request to api/hello")
    @Test
    public void test3() {

        Response response = when().get(baseUrl + "/api/hello");

        //verify Status code
        assertEquals(200, response.statusCode());

        //verify content type
        assertEquals("text/plain;charset=UTF-8", response.contentType());


        //verify there is header named Date
        assertTrue(response.headers().hasHeaderWithName("Date"));

        //how to get any header from response using header key
        //to get any header value we use response.header(String headerName)
        System.out.println("response.thenReturn(\"Content-Length\") = " + response.header("Content-Length"));

        System.out.println(response.header("Date"));

        assertEquals("17", response.header("Content-Length"));

        assertEquals("Hello from Sparta", response.body().asString());
    }





}
