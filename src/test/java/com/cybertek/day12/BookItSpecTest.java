package com.cybertek.day12;

import com.cybertek.utilities.BookItTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class BookItSpecTest extends BookItTestBase {

    @Test
    public void test1() {

        //send a get request to /api/users/me endpoint as a teacher
        //verify status code and content type

        given()
                .spec(teacherReqSpec)
                .when()
                .get("/api/users/me")
                .then()
                .spec(responseSpec);


    }

    @Test
    public void test2() {

        given()
                .spec(userReqSpec("student-member"))
                .when()
                .get("/api/users/me")
                .then()
                .spec(userCheck("Lorette", "Bradnum"));
    }

}
