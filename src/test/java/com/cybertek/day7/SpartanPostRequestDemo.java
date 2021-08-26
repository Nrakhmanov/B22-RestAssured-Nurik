package com.cybertek.day7;

import com.cybertek.pojo.Spartan;
import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;


public class SpartanPostRequestDemo extends SpartanTestBase {

     /*
    Given accept type and Content type is JSON
    And request json body is:
    {
      "gender":"Male",
      "name":"Severus",
      "phone":8877445596
   }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
    "A Spartan is Born!" message
    and same data what is posted
 */

    @Test
    public void test1() {

       String requestJsonBody = "{\"gender\":\"Female\",\n" +
               "\"name\":\"Miku\",\n" +
               "\"phone\":1234567899}";


        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(requestJsonBody)
                .when()
                .post("/api/spartans/");

        assertThat(response.statusCode(), Matchers.is(201));

        assertThat(response.contentType(), Matchers.is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";

        assertThat(response.path("success"), Matchers.is(expectedResponseMessage));

        assertThat(response.path("data.name"), Matchers.is("Miku"));
        assertThat(response.path("data.gender"), Matchers.is("Female"));
        assertThat(response.path("data.phone"), Matchers.is(1234567899));

    }

     /*
    Given accept type and Content type is JSON
    And request json body is:
    {
      "gender":"Male",
      "name":"Severus",
      "phone":8877445596
   }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
    "A Spartan is Born!" message
    and same data what is posted
 */

    @Test
    public void test2() {

        //create a map to keep request JSON body info

        Map<String, Object> requestJsonMap = new LinkedHashMap<>();

        requestJsonMap.put("name", "Miku");
        requestJsonMap.put("gender", "Female");
        requestJsonMap.put("phone", 1234567899L);


        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(requestJsonMap).log().all()
                .when()
                .post("/api/spartans/");

        assertThat(response.statusCode(), Matchers.is(201));

        assertThat(response.contentType(), Matchers.is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";

        assertThat(response.path("success"), Matchers.is(expectedResponseMessage));

        assertThat(response.path("data.name"), Matchers.is("Miku"));
        assertThat(response.path("data.gender"), Matchers.is("Female"));
        assertThat(response.path("data.phone"), Matchers.is(1234567899));

        response.prettyPrint();
    }

    @Test
    public void test3() {

        //create one object from pojo class
        Spartan spartan = new Spartan();
        spartan.setName("Miku");
        spartan.setGender("Female");
        spartan.setPhone(1234567899L);

        System.out.println("spartan = " + spartan);

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(spartan).log().all()
                .when()
                .post("/api/spartans/");

        assertThat(response.statusCode(), Matchers.is(201));

        assertThat(response.contentType(), Matchers.is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";

        assertThat(response.path("success"), Matchers.is(expectedResponseMessage));

        assertThat(response.path("data.name"), Matchers.is("Miku"));
        assertThat(response.path("data.gender"), Matchers.is("Female"));
        assertThat(response.path("data.phone"), Matchers.is(1234567899));

        response.prettyPrint();
    }
    //homework
    //create one spartanUtil class
    //create a static methid that returns Map<String, Object>
    //with dummy info

    @Test
    public void test4() {

        //create one object from pojo class
        Spartan spartan = new Spartan();
        spartan.setName("Miku");
        spartan.setGender("Female");
        spartan.setPhone(1234567899L);

        System.out.println("spartan = " + spartan);
        String expectedResponseMessage = "A Spartan is Born!";
        int idFromPost = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(spartan).log().all()
                .when()
                .post("/api/spartans/")
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("success", Matchers.is(expectedResponseMessage))
                .extract().jsonPath().getInt("data.id");


        System.out.println("idFromPost = " + idFromPost);


        //send a get request to id
        Spartan spartanPosted = given().accept(ContentType.JSON)
                .and().pathParam("id", idFromPost)
                .when().get("/api/spartans/{id}")
                .then()
                .statusCode(200).log().all().extract().as(Spartan.class);


        assertThat(spartanPosted.getName(), Matchers.is(spartan.getName()));
        assertThat(spartanPosted.getGender(), Matchers.is(spartan.getGender()));
        assertThat(spartanPosted.getPhone(), Matchers.is(spartan.getPhone()));
        assertThat(spartanPosted.getId(), Matchers.is(idFromPost));

    }
}










