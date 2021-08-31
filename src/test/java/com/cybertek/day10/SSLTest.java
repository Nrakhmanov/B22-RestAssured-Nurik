package com.cybertek.day10;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class SSLTest {

    @Test
    public void test1() {

        given().relaxedHTTPSValidation(). //to send request even if there is no valid certificate
        when().get("https://untrusted-root.badssl.com")
                .prettyPrint();
    }

    @Test
    public void test2() {

        given()
                .keyStore("pathtofile", "password")
                .when().get("apiurl");
    }
}
