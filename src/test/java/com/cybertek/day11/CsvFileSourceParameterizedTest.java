package com.cybertek.day11;

import io.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class CsvFileSourceParameterizedTest {

    // Write a parameterized test for this request
    // Get the data from csv source
    // GET https://api.zippopotam.us/us/{state}/{city}

    @ParameterizedTest
    @CsvFileSource(resources = "/zipcode.csv", numLinesToSkip = 1)
    public void test1(String state, String city, int zipCount) {

        System.out.println("state = " + state);
        System.out.println("city = " + city);
        System.out.println("zipCount = " + zipCount);

        //send request and verify place number matches with zipcount

        RestAssured.given()
                .baseUri("https://api.zippopotam.us")
                .pathParam("state", state)
                .pathParam("city", city)
                .log().uri()
                .when()
                .get("/us/{state}/{city}")
                .then()
                .statusCode(200)
                .body("places", Matchers.hasSize(zipCount));


    }
}
