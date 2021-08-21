package com.cybertek.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class HRTestBase {

    @BeforeAll
    public static void init() {
        baseURI = "http://52.91.229.67:1000/ords/hr";


        String dbUrl = "jdbc:oracle:thin:@52.91.229.67:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";
    }
}
