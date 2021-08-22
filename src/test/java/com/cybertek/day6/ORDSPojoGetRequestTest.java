package com.cybertek.day6;

import com.cybertek.pojo.Employee;
import com.cybertek.pojo.Region;
import com.cybertek.pojo.Regions;
import com.cybertek.utilities.HRTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class ORDSPojoGetRequestTest extends HRTestBase {

    @Test
    public void test1() {

        JsonPath jsonPath = RestAssured.get("/regions").then().statusCode(200)
                .extract().jsonPath();

        Region region1 = jsonPath.getObject("items[0]", Region.class);

        System.out.println("region1.getRegion_id() = " + region1.getRegionId());
        System.out.println("region1.getRegion_name() = " + region1.getRegion_name());

        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());


    }

    @Test
    public void test2() {

        List<Region> regionList = RestAssured.given().accept(ContentType.JSON)
                .when().get("/regions").then().statusCode(200)
                .extract().jsonPath().getList("items", Region.class);

        System.out.println("regionList.get(0).getRegion_name() = " + regionList.get(0).getRegion_name());
        System.out.println("regionList.get(1).getRegion_name() = " + regionList.get(1).getRegion_name());
        System.out.println("regionList.get(1).getRegionId() = " + regionList.get(1).getRegionId());

    }

    @Test
    public void employeeGet() {

        Employee employee = RestAssured.get("/employees").then().statusCode(200)
                .extract().jsonPath().getObject("items[0]", Employee.class);

        System.out.println(employee);

        System.out.println("employee.getFirstName() = " + employee.getFirstName());
        System.out.println("employee.getLastName() = " + employee.getLastName());


    }


    @Test
    public void regionPojoTest() {
        //since we prepare POJO also for all properties we dont need to use any path so as() method is enough
        Regions regions = RestAssured.get("/regions").then().statusCode(200)
                .extract().response().as(Regions.class);

        assertThat(regions.getCount(), Matchers.is(4));

        //create empty list to store values
        List<String> regionNames = new ArrayList<>();
        List<Integer> regionIDs = new ArrayList<>();

        //get list of regions out of region object
        List<Region> items = regions.getItems();

        //loop through each of region, save their ids and names to empty lists that we prepared
        for (Region region : items) {

            regionIDs.add(region.getRegionId());
            regionNames.add(region.getRegion_name());

        }

        List<Integer> expectedRegionIDs = Arrays.asList(1,2,3,4);
        List<String> expectedRegionNames = Arrays.asList("Europe", "Americas", "Asia", "Middle East and Africa");

        assertThat(regionNames, Matchers.is(expectedRegionNames) );
        assertThat(regionIDs, Matchers.is(expectedRegionIDs) );





    }

}
