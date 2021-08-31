package com.cybertek.day8;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BookItTest {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "https://cybertek-reservation-api-qa.herokuapp.com";

    }

    String accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMzkiLCJhdWQiOiJzdHVkZW50LXRlYW0tbGVhZGVyIn0._vM1-eRoS7SsHu6T-QPdJoEdA8LSwnxUvvTTbhV-8ms";

    @Test
    public void testAuth1() {

        given().header("Authorization", accessToken)
                .and().accept(ContentType.JSON)
                .get("/api/campuses")
                .then().statusCode(200)
                .log().all();
    }
}
