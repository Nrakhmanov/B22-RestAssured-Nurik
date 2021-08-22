package com.cybertek.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpartanTestBase {


    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://52.91.229.67:8000/";

        String dbUrl = "jdbc:oracle:thin:@52.91.229.67:1521:xe";
        String dbUsername = "SP";
        String dbPassword = "SP";

       // DBUtils.createConnection(dbUrl,dbUsername,dbPassword);
    }

    @AfterAll
    public static void tearDown() {
       // DBUtils.destroy();
    }
}
