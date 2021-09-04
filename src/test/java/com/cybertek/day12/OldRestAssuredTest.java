package com.cybertek.day12;

import com.cybertek.utilities.SpartanNewBase;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class OldRestAssuredTest extends SpartanNewBase {

    @Test
    public void getAllSpartan() {

        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin", "admin")
                .log().all()
        .when()
                .get("spartans")
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]", Matchers.is(10))
                .body("id[5]", Matchers.is(199))
                .log().all();
    }

    @Test
    public void test2() {

        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin", "admin")
                .log().all()
        .expect()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .body("id[0]", Matchers.is(10))
                .body("id[5]", Matchers.is(199))
                .logDetail(LogDetail.ALL)
        .when()
                .get("spartans");
    }

}
