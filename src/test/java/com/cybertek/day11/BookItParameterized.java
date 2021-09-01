package com.cybertek.day11;

import com.cybertek.utilities.ExcelUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;

public class BookItParameterized {

    public static List<Map<String, String>> excelData() {
        ExcelUtil data = new ExcelUtil("src/test/resources/BookItQa3.xlsx", "QA3");


         return data.getDataList();
    }

    @ParameterizedTest
    @MethodSource("excelData")
    public void test1(Map<String, String> user) {

        System.out.println("user.get(\"email\") = " + user.get("email"));
        System.out.println("user.get(\"password\") = " + user.get("password"));


        RestAssured.given()
                .accept(ContentType.JSON)
                .baseUri("https://cybertek-reservation-api-qa3.herokuapp.com")
                .queryParams(user)
         .when()
                .get("/sign")
        .then()
                .statusCode(200)
                .log().body();


    }


}
