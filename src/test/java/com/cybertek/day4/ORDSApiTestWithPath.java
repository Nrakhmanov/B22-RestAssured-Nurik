package com.cybertek.day4;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithPath extends HRTestBase {

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");


        assertEquals(200, response.statusCode());


        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        System.out.println("response.path(\"HasMore\") = " + response.path("hasMore"));

        String firstCountryID = response.path("items[0].country_id");

        System.out.println("firstCountryID = " + firstCountryID);

        String secondCountryName = response.path("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        String thirdcountryName = response.path("items[2].country_name");
        System.out.println("thirdcountryName = " + thirdcountryName);

        String thirdCountryLink = response.path("items[2].links[0].href");
        System.out.println("thirdCountryLink = " + thirdCountryLink);

        assertEquals("http://3.92.136.109:1000/ords/hr/countries/CA", response.path("items[2].links[0].href"));


        String fourthCountyName = response.path("items[3].country_name");
        System.out.println("fourthCountyName = " + fourthCountyName);

        String fifthCountyName = response.path("items[4].country_name");
        System.out.println("fifthCountyName = " + fifthCountyName);

        System.out.println("neighbors = " + fourthCountyName + " - " + fifthCountyName);

        List<String> countryNames = response.path("items.country_name");

        System.out.println("countryNames = " + countryNames);

        List<Integer> allRegionsIDs = response.path("items.region_id");

        for (Integer regionsID : allRegionsIDs) {

            System.out.println("regionsID = " + regionsID);
            assertEquals(2, regionsID);

        }

        JsonPath jsonPath = response.jsonPath();

        String country1 = jsonPath.getString("items[0].country_name");
        System.out.println("country1 = " + country1);

    }

    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .queryParams("q", "{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees");


        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        assertTrue(response.body().asString().contains("IT_PROG"));

        List<String> allJobIDs = response.path("items.job_id");


        for (String jobID : allJobIDs) {
            System.out.println("jobID = " + jobID);
            assertEquals("IT_PROG", jobID);

        }

        List<String> allNames = response.path("items.names");

    }

}
