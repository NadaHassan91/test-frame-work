package api_test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;


public class API {

    String baseURL = "https://case-api.trella.app";

    @Test
    public void TestListShipment() {

        Response response = given().header("Authorization", "nh.nadahassan91@gmail.com").when().get(baseURL + "/marketplace");

        response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON);

        ResponseBody body = response.getBody();

        ListShipment[] shipmentList = body.as(ListShipment[].class);
        Assert.assertEquals(7, shipmentList.length);
        for(int i =0; i< shipmentList.length; i++) {
            Assert.assertNotNull(shipmentList[i].commodity);
            Assert.assertNotNull(shipmentList[i].key);
            Assert.assertNotNull(shipmentList[i].numberOfBids);
            Assert.assertNotNull(shipmentList[i].price);
            Assert.assertNotNull(shipmentList[i].vehicleType);
            Assert.assertNotNull(shipmentList[i].addresses);

        }
    }

    @Test
    public void TestListShipmentWithFilters() {

        Response response = given().header("Authorization", "nh.nadahassan91@gmail.com").when().get(baseURL + "/marketplace?lng=30&lat=30");

        response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON);

        ResponseBody body = response.getBody();

        ListShipment[] shipmentList = body.as(ListShipment[].class);
        Assert.assertEquals(3, shipmentList.length);
        for(int i =0; i< shipmentList.length; i++) {
            Assert.assertNotNull(shipmentList[i].commodity);
            Assert.assertNotNull(shipmentList[i].key);
            Assert.assertNotNull(shipmentList[i].numberOfBids);
            Assert.assertNotNull(shipmentList[i].price);
            Assert.assertNotNull(shipmentList[i].vehicleType);
            Assert.assertNotNull(shipmentList[i].addresses);

        }
    }



}
