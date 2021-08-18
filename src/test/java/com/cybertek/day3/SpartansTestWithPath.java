package com.cybertek.day3;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartansTestWithPath {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://3.92.136.109:8000";
    }

    /*
     Given accept type is json
     And path param id is 10
     When user sends a get request to "api/spartans/{id}"
     Then status code is 200
     And content-type is "application/json"
     And response payload values match the following:
          id is 10,
          name is "Lorenza",
          gender is "Female",
          phone is 3312820936

     */


    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 10)
                .when().get("/api/spartans/{id}");


        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());


        //verify each json key has specific value

        System.out.println(response.path("id").toString());
        System.out.println(response.path("name").toString());
        System.out.println(response.path("gender").toString());
        System.out.println(response.path("phone").toString());

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        Assertions.assertEquals(10, id);
        Assertions.assertEquals("Lorenza", name);
        Assertions.assertEquals("Female", gender);
        Assertions.assertEquals(3312820936l, phone);


    }

    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        response.prettyPrint();

        int firstId = response.path("id[0]");

        System.out.println("firstId = " + firstId);

        String firstName = response.path("name[0]");

        System.out.println("firstName = " + firstName);

        String lastFirstName = response.path("name[-1]");
        System.out.println("lastFirstName = " + lastFirstName);


        List<String> names = response.path("name");
        System.out.println(names);

        for (String n : names) {
            System.out.println(n);

        }

    }
}
