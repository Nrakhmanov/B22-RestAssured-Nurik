package com.cybertek.day7;

import com.cybertek.pojo.Spartan;
import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PutAndPatchRequestDemo extends SpartanTestBase {

    @Test
    public void requestPUT() {

        Map<String, Object> putRequestMap = new LinkedHashMap<>();

        putRequestMap.put("name", "MikuHansuke");
        putRequestMap.put("gender", "Female");
        putRequestMap.put("phone", 98765432110L);

        given().contentType(ContentType.JSON)
                .and().pathParam("id", 111)
                .body(putRequestMap).log().body()
                .when().put("/api/spartans/{id}")
                .then()
                .statusCode(204);

        //send a get request after update, make sure updated field changed, or the new info matching
        //with requestBody that we send

        given().accept(ContentType.JSON)
                .pathParam("id", 111)
                .when().get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .extract().as(Spartan.class);
    }

    @Test
    public void requestPATCH() {

        Map<String, Object> putRequestMap = new LinkedHashMap<>();

//        putRequestMap.put("name", "MikuHansuke");
//        putRequestMap.put("gender", "Female");
        putRequestMap.put("phone", 88865432110L);

        given().contentType(ContentType.JSON)
                .and().pathParam("id", 111)
                .body(putRequestMap).log().body()
                .when().patch("/api/spartans/{id}")
                .then()
                .statusCode(204);
    }

    @Test
    public void requestDELETE() {

        int idToDelete = 105;

        given().pathParam("id", idToDelete)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);

        given().accept(ContentType.JSON)
                .and().pathParam("id", idToDelete)
                .when().get("/api/spartans/{id}")
                .then().statusCode(404);
    }
}
