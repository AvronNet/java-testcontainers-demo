package com.avronnet.listings.api.pg;

import com.avronnet.listings.persistance.models.Listing;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PgListingsControllerTests extends PostgresTestBase {

    @Test
    @Order(1)
    @DisplayName("Create Listing - Success")
    void createListingIT() throws JsonProcessingException {

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

    @Disabled("Disabled until needed")
    @Test
    @Order(2)
    @DisplayName("Find and delete listing - Success")
    void findAndDeleteListingIT() throws JsonProcessingException {

        var listingTitle = "Listing Name";
        ExtractableResponse<Response> response = RestAssured
                .when()
                .get("http://localhost:" + port + "/api/listings/title/" + listingTitle)
                .then()
                .statusCode(200)
                .extract();

        // Execute the service call
        var responseBody = response.body().asString();
        var returnedListings = objectMapper.readValue(responseBody, Listing[].class);

        // Assert that we have some listings
        Assertions.assertTrue(returnedListings.length > 0, "No Listings returned");
        var listing = returnedListings[0];

        RestAssured
                .when()
                .delete("http://localhost:" + port + "/api/listings/" + listing.getId())
                .then()
                .statusCode(200);
    }
}
