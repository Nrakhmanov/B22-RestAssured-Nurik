package com.cybertek.day5;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ORDSHamcrestTest extends HRTestBase {


    @Test
    public void regionTest() {

        given()
                .accept(ContentType.JSON)
                .queryParam("q","{\"job_id\":\"IT_PROG\"}")
        .when()
                .get("/employees")
        .then()
                .statusCode(200)
                .and()
                .contentType("application/json")
                .and().assertThat()
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                .body("items.first_name", containsInRelativeOrder("Alexander","Bruce", "David", "Valli", "Diana"));
               // .body("items.email", containsInAnyOrder("ANUHOLD","BERNST","DAUSTIN","VPATABAL","DLORENTZ"));

    }

    @Test
    public void employeesTest() {
        //we want to chain and also get response object

        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json")
                .and().assertThat()
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                .extract().jsonPath();

       assertThat(jsonPath.getList("items.first_name"), hasSize(5));
       assertThat(jsonPath.getList("items.first_name"), containsInRelativeOrder("Alexander","Bruce", "David", "Valli", "Diana"));





    }
}
