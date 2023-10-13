package com.avronnet.listings.api.h2;

import com.avronnet.listings.persistance.models.Listing;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class H2ListingsControllerTests extends H2TestBase {

    @Test
    @DisplayName("Test findById Success")
    void testFindById() throws JsonProcessingException {

        Listing listing = new Listing(null, "Listing Name", "Description");
        String createBody = objectMapper.writeValueAsString(listing);
        ExtractableResponse<Response> response = RestAssured
                .given()
                .contentType("application/json")
                .body(createBody)
                .when()
                .post("http://localhost:" + port + "/api/listings")
                .then()
                .statusCode(201)
                .extract();

        // Execute the service call
        var responseBody = response.body().asString();
        Listing returnedListing = objectMapper.readValue(responseBody, Listing.class);

        // Assert the response
        Assertions.assertNotNull(returnedListing.getId(), "Created listing return data is missing ID");
        Assertions.assertEquals(listing.getTitle(), returnedListing.getTitle(), "Listing title returned was not as expected");
        Assertions.assertEquals(listing.getDescription(), returnedListing.getDescription(), "Listing description returned was not as expected");
    }
}
