package com.cybertek.day4;

import com.cybertek.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class ORDSApiWithJsonPath extends HRTestBase {

    @DisplayName("GET request to countries")
    @Test
    public void test1() {

        Response response = get("/countries");

        JsonPath jsonPath = response.jsonPath();

        String secondCountryName = jsonPath.getString("items[1].country_name");
        System.out.println("name = " + secondCountryName);

        List<String> allCountryIDs = jsonPath.getList("items.country_id");
        System.out.println(allCountryIDs);


        List<Object> countryNamesWithRegionId2 = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println(countryNamesWithRegionId2);
    }

    @DisplayName("GET request with employees")
    @Test
    public void test2() {

        Response response = given().queryParam("limit", 107)
                                   .when().get("/employees");

        JsonPath jsonPath = response.jsonPath();

        List<Object> allEmails = jsonPath.getList("items.findAll {it.job_id == \"IT_PROG\"}.email");
        System.out.println(allEmails);

        List<Object> names = jsonPath.getList("items.findAll {it.salary > 10000}.first_name");

        System.out.println(names);

        String kingFirstName = jsonPath.getString("items.max {it.salary}.first_name");
        System.out.println("kingFirstName = " + kingFirstName);

        String kingfirstNameWithPathMethod = response.path("items.max {it.salary}.first_name");
        System.out.println("kingfirstNameWithPathMethod = " + kingfirstNameWithPathMethod);


    }


}
