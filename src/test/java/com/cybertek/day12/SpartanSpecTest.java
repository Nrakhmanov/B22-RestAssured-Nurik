package com.cybertek.day12;

import com.cybertek.utilities.SpartanNewBase;
import io.restassured.RestAssured.*;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class SpartanSpecTest extends SpartanNewBase {

    @Test
    public void test1() {

//        RequestSpecification requestSpec = given()
//                                                 .accept(ContentType.JSON)
//                                                 .and()
//                                                 .auth().basic("admin", "admin")
//                                                 .log().all();
//
//        ResponseSpecification responseSpec = expect()
//                                                     .statusCode(200)
//                                                     .and()
//                                                     .contentType(ContentType.JSON)
//                                                     .logDetail(LogDetail.ALL);
        given()
                .spec(requestSpec)
        .when()
                .get("/spartans")
        .then()
                .spec(responseSpec);

    }

    @Test
    public void test2() {

        given()
                .spec(requestSpec)
                .pathParam("id", 15)
        .when()
                .get("/spartans/{id}")
        .then()
                .spec(responseSpec);
    }

    @Test
    public void test3() {

        given()
                .spec(requestSpec)
                .and()
                .queryParams("nameContains", "j",
                        "gender", "Female")
                .when()
                .get("/spartans/search")
                .then()
                .spec(responseSpec)
                .body("numberOfElements", is(7));
    }
}
