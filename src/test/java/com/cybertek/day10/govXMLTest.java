package com.cybertek.day10;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class govXMLTest {

    //send a get request to
    //https://data.ct.gov/api/views/qm34-pq7e/rows.xml
    //get all the years
    //get all unknowns
    //get 2006 other
    //get 2007 _address

    @Test
    public void test1() {

        Response response = given().get("https://data.ct.gov/api/views/qm34-pq7e/rows.xml")
                .then()
                .statusCode(200)
                .extract().response();

        XmlPath xmlPath = response.xmlPath();

        List<Object> list = xmlPath.getList("response.row.row.year");
        System.out.println("list = " + list);

        List<Object> unknownList = xmlPath.getList("response.row.row.unknown");
        System.out.println("unknownList = " + unknownList);

        int other2005 = xmlPath.getInt("response.row.row[2].other");
        System.out.println("other2005 = " + other2005);

        String address = xmlPath.getString("response.row.row[4].@_address");
        System.out.println("address = " + address);


    }
}
