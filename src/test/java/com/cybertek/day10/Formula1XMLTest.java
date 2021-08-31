package com.cybertek.day10;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class Formula1XMLTest {

    @BeforeAll
    public static void setUp() {
        baseURI = "http://ergast.com";
        basePath = "/api/f1";

    }

    @Test
    public void test1() {
        Response response = given()
                .pathParam("driver", "alonso")
                .when()
                .get("/drivers/{driver}")
                .then().statusCode(200).log().all()
                .extract().response();


        XmlPath xmlPath = response.xmlPath();

        String name = xmlPath.getString("MRData.DriverTable.Driver.GivenName");
        System.out.println("name = " + name);

        String lastName = xmlPath.getString("MRData.DriverTable.Driver.FamilyName");
        System.out.println("lastName = " + lastName);

        //to get attribute we use @ sign
        String id = xmlPath.getString("MRData.DriverTable.Driver.@driverId");
        System.out.println("id = " + id);

        String code = xmlPath.getString("MRData.DriverTable.Driver.@code");
        System.out.println("code = " + code);

        String url = xmlPath.getString("MRData.DriverTable.Driver.@url");
        System.out.println("url = " + url);


    }
}
