package com.cybertek.day11;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;

public class ParameterizedTestInJunit5 {

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,5,8,13,21,34,55})
    public void testMultipleNumbers(int number) {

        System.out.println("number = " + number);

        Assertions.assertTrue(number >5);

    }



    @ParameterizedTest
    @ValueSource(strings = {"Alpha", "Bravo", "Charlie", "Delta"})
    public void testMultipleNames(String name) {

        System.out.println("name = " + name);


    }
    // SEND GET REQUEST TO https://api.zippopotam.us/us/{zipcode}
    // with these zipcodes 22030,22031, 22032, 22033 , 22034, 22035, 22036
    // check status code 200
    @ParameterizedTest
    @ValueSource(ints = {22030, 22031, 22032,22034, 22035, 22036 })
    public void testZipcode(int zipcode) {

        given()
                .baseUri("https://api.zippopotam.us")
                .and().pathParam("zipcode", zipcode)
                .log().all() //request log
        .when()
                .get("/us/{zipcode}")
        .then().log().all() //response log
                .statusCode(200);



        }
    }

