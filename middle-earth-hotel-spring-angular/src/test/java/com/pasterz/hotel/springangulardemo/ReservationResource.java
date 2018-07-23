package com.pasterz.hotel.springangulardemo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import com.pasterz.hotel.springangulardemo.rest.ResourceConstants;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
  classes = SpringAngularDemoApplication.class,
  webEnvironment = WebEnvironment.RANDOM_PORT
)
public class ReservationResource {

  @LocalServerPort private int port;

  @Before
  public void setUp() throws Exception {

    RestAssured.port = Integer.valueOf(port);
    RestAssured.basePath = ResourceConstants.ROOM_RESERVATION_V1;
    RestAssured.baseURI = "http://localhost";
  }

  @Test
  public void test() {
    given().when().get("/" + 1).then().statusCode(200);
  }

  @Test
  public void testGET() {
    given().when().get("?checkin=2017-03-18&checkout=2016-03-18").then().statusCode(200);
  }

  @Test
  public void testPOST() {
    given()
        .contentType("application/json")
        .body("{\"roomId\" : 1,\"checkin\": \"2018-07-06\",\"checkout\": \"2018-09-06\" }")
        .when()
        .post()
        .then()
        .statusCode(201);
  }
}
