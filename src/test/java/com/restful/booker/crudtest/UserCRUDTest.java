package com.restful.booker.crudtest;

import com.restful.booker.model.BookingPojo;
import com.restful.booker.testbase.TestBase;
import com.restful.booker.utils.TestUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {

    @Test
    public void getAllBookingIds() {
        Response response = given()
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void getSingleBookingIds() {
        Response response = given()
                .when()
                .get("/68");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void createBooking() {

        String firstName = TestUtils.getRandomValue() + "Test";
        String lastName = TestUtils.getRandomValue() + "Data";

        BookingPojo bookingPojo = new BookingPojo();
        HashMap<String, String> bookingDatesData = new HashMap<>();
        bookingDatesData.put("checkin", "2023-01-01");
        bookingDatesData.put("checkout", "2023-01-14");
        bookingPojo.setFirstName(firstName);
        bookingPojo.setLastName(lastName);
        bookingPojo.setTotalPrice(324);
        bookingPojo.setDepositPaid(true);
        bookingPojo.setBookingDates(bookingDatesData);
        bookingPojo.setAdditionalNeeds("Lunch");
        Response response = given()
                .header("Content-Type", "application/json")

                .body(bookingPojo)
                .when()
                .post("/booking");
        response.then().statusCode(404);
        response.prettyPrint();
    }

    @Test
    public void updateBooking() {


        HashMap<String, String> bookingDatesData = new HashMap<>();
        bookingDatesData.put("checkin", "2023 - 10 - 01");
        bookingDatesData.put("checkout", "2023 - 10 - 20");

        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstName("Test");
        bookingPojo.setLastName("Data");
        bookingPojo.setTotalPrice(650);
        bookingPojo.setDepositPaid(true);
        bookingPojo.setBookingDates(bookingDatesData);
        bookingPojo.setAdditionalNeeds("Lunch");
        Response response =
                given().log().all()
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .body(bookingPojo)
                        .when()
                        .put("/booking/104");
        response.then().statusCode(404);
        response.prettyPrint();

    }

    @Test
    public void partialUpdateBooking() {
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstName("Data");
        bookingPojo.setLastName("Set");
        Response response =
                given().log().all()
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                        .body(bookingPojo)
                        .when()
                        .patch("/48");
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void deleteBooking() {
        Response response = given().log().all()
                .header("Content-Type", "application/json")
                .header("Cookie", "token=abc123")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .delete("/76");
        response.then().statusCode(201);
        response.prettyPrint();
    }

    @Test
    public void pingCheck() {
        Response response = given().log().all()
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .get("https://restful-booker.herokuapp.com/ping");
        response.then().statusCode(201);
        response.prettyPrint();

    }
}
