package com.cybertek.day8;

import com.cybertek.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class SpartanWithAuthTest extends SpartanAuthTestBase {


    @Test
    public void test1() {

        given().accept(ContentType.JSON)
                .when().get("api/spartans")
                .then().statusCode(401)
                .log().all();


    }

    @Test
    public void testAdmin() {

        given()
                .auth().basic("admin", "admin")
                .and().accept(ContentType.JSON)
        .when()
                .get("api/spartans")
        .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void testEditor() {

        given()
                .auth().basic("editor", "editor")
                .and().accept(ContentType.JSON)
                .pathParam("id", 37)
        .when()
                .delete("api/spartans/{id}")
        .then()
                .statusCode(403)
                .log().all();
    }


}
