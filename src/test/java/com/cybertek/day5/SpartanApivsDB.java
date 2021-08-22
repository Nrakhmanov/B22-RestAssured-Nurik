package com.cybertek.day5;

import com.cybertek.utilities.DBUtils;
import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SpartanApivsDB extends SpartanTestBase {

    @Test
    public void testDB1() {
        //get name, id, gender, phone from database


        String query = "select spartan_id, name, gender, phone from spartans\n" +
        "where spartan_id = 15";

        //get same info from api

        Map<String, Object> dbMap = DBUtils.getRowMap(query);
        System.out.println("dbMap = " + dbMap);

        //compare

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when()
                .get("api/spartans/{id}")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json")
                .extract().response();

        //deserilalization here Json to java
        Map<String, Object> apiMap = response.as(Map.class);

        System.out.println(apiMap);

        //compare database vs api --> we assume expected result is db
        assertThat(apiMap.get("id").toString(),is(dbMap.get("SPARTAN_ID").toString()));
        assertThat(apiMap.get("name"),is(dbMap.get("NAME")));
        assertThat(apiMap.get("gender"),is(dbMap.get("GENDER")));
        assertThat(apiMap.get("phone").toString(),is(dbMap.get("PHONE").toString()));


    }
}
