package com.cybertek.day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {

    String url = "http://3.92.136.109:8000/api/spartans";

    @Test
    public void test1() {

        Response response = RestAssured.get(url);

        //print status
        System.out.println(response.statusCode());

        //print response body
        response.prettyPrint();

    }


}
