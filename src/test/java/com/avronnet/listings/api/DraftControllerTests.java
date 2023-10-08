package com.avronnet.listings.api;

import com.avronnet.listings.api.h2.H2TestBase;
import com.avronnet.listings.persistance.models.Draft;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DraftControllerTests extends H2TestBase {

    @Test
    @DisplayName("Test findById Success")
    void testFindById() throws JsonProcessingException {

        Draft draft = new Draft("randomID1", "Draft Name", "Description");
        String createBody = objectMapper.writeValueAsString(draft);
        ExtractableResponse<Response> response = RestAssured
                .given()
                .contentType("application/json")
                .body(createBody)
                .when()
                .post("http://localhost:" + port + "/api/drafts")
                .then()
                .statusCode(201)
                .extract();

        // Execute the service call
        var responseBody = response.body().asString();
        Draft returnedDraft = objectMapper.readValue(responseBody, Draft.class);

        // Assert the response
        Assertions.assertEquals(draft.getId(), returnedDraft.getId(), "The draft ID returned was not the same as the expected draft ID");
    }
}
