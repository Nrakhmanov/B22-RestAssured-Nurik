package com.cybertek.day4;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CBTrainingApiWithJsonPath {

    @BeforeAll
    public static void init() {
        baseURI = "http://api.cybertektraining.com";
    }


    //send a get request to student id 23401 as a path parameter and accept header application/json
    //verify status code /content type /Content-Encoding = gzip
    //verify Date header exists
    //assert that
            /*
                firstName Vera
                batch 14
                section 12
                emailAddress aaa@gmail.com
                companyName Cybertek
                state IL
                zipCode 60606

                using JsonPath
             */


    @DisplayName("GET request to individual student")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 23401)
                .when().get("/student/{id}");

        JsonPath jsonPath = response.jsonPath();

        assertEquals(200, response.statusCode());
        assertEquals("application/json;charset=UTF-8", response.contentType());

        assertTrue(response.headers().hasHeaderWithName("Date"));

        assertEquals("gzip", response.header("Content-Encoding"));

        String firstName = jsonPath.getString("students[0].firstName");

        System.out.println("firstName = " + firstName);

        assertEquals("Vera", firstName);

        String batch = jsonPath.getString("students[0].batch");
        System.out.println("batch = " + batch);

        assertEquals("14", batch);

        String section = jsonPath.getString("students[0].section");
        System.out.println("section = " + section);

        assertEquals("12", section);

        String email = jsonPath.getString("students[0].contact.emailAddress");

        System.out.println("email = " + email);

        assertEquals("aaa@gmail.com", email);

        String company = jsonPath.getString("students[0].company.companyName");
        System.out.println("company = " + company);

        assertEquals("Cybertek", company);

        String state = jsonPath.getString("students[0].company.address.state");
        System.out.println("state = " + state);

        assertEquals("IL", state);

        int zipcode = jsonPath.getInt("students[0].company.address.zipCode");
        System.out.println("zipcode = " + zipcode);

        assertEquals(60606, zipcode);


    }
}

