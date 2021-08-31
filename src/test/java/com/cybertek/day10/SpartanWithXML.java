package com.cybertek.day10;

import com.cybertek.utilities.SpartanAuthTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class SpartanWithXML extends SpartanAuthTestBase {

    @Test
    public void getSpartanXML() {

        given().accept(ContentType.XML)
                .and().auth().basic("admin", "admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .contentType("application/xml;charset=UTF-8")
                .body("List.item[1].name", is("IraFuse"))
                .body("List.item[1].gender", is("Female"))
                .log().all();

    }

    @Test
    public void testXMLPath() {
        Response response = given().accept(ContentType.XML)
                .and().auth().basic("admin", "admin")
                .when()
                .get("/api/spartans");

        XmlPath xmlPath = response.xmlPath();

        String name = xmlPath.getString("List.item[1].name");
        System.out.println("name = " + name);

        int id = xmlPath.getInt("List.item[2].id");
        System.out.println("id = " + id);

        List<Object> names = xmlPath.getList("List.item.name");
        System.out.println("names = " + names);



    }


}
