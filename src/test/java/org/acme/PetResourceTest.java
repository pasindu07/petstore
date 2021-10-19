package org.acme;

import io.quarkus.test.junit.QuarkusTest;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.allOf;

@QuarkusTest
public class PetResourceTest {

    @Test
    public void testPetEndpoint() {
        given()
          .when().get("/v1/petStore")
          .then()
             .statusCode(200);
    }

    @Test
    public void testPetIdEndpoint() {
        given()
          .when().get("/v1/petStore/1")
          .then()
             .statusCode(200);
    }

    @Test
    public void testAddPetEndpoint() {
        given()
          .when().get("/v1/petStore/add/5/2/Scooby/Dog")
          .then()
             .statusCode(200);
    }

    @Test
    public void testDeletePetEndpoint() {
        given()
          .when().get("/v1/petStore/2")
          .then()
             .statusCode(200);
    }

    @Test
    public void testUpdatePetNameEndpoint() {
        given()
          .when().get("/v1/petStore/updateName/1/Jimmy")
          .then()
             .statusCode(200);
    }

    @Test
    public void testUpdatePetAgeEndpoint() {
        given()
          .when().get("/v1/petStore/updateAge/1/4")
          .then()
             .statusCode(200);
    }

    @Test
    public void testUpdatePetTypeEndpoint() {
        given()
          .when().get("/v1/petStore/updateType/1/Cat")
          .then()
             .statusCode(200);
    }

    @Test
    public void testSearchPetByNameEndpoint() {
        given()
          .when().get("/v1/petStore/searchPetName/Boola")
          .then()
             .statusCode(200);
    }

    @Test
    public void testSearchPetByIdEndpoint() {
        given()
          .when().get("/v1/petStore/searchPetId/2")
          .then()
             .statusCode(200);
    }


    @Test
    public void testSearchPetByAgeEndpoint() {
        given()
          .when().get("/v1/petStore/searchPetAge/4")
          .then()
             .statusCode(200);
    }

    @Test
    public void testSearchPetByTypeEndpoint() {
        given()
          .when().get("/v1/petStore/searchPetType/Dog")
          .then()
             .statusCode(200);
    }

    @Test
    public void testGetAllTypesEndpoint() {
        given()
          .when().get("/v1/petStore/allTypes")
          .then()
             .statusCode(200);
    }

    @Test
    public void testAddTypesEndpoint() {
        given()
          .when().get("/v1/petStore/addType/5/Reptile")
          .then()
             .statusCode(200);
    }

    @Test
    public void testUpdatingTypesEndpoint() {
        given()
          .when().get("/v1/petStore/updatingTypeName/3/Reptile")
          .then()
             .statusCode(200);
    }

    @Test
    public void testDeletingTypesEndpoint() {
        given()
          .when().get("/v1/petStore/deleteType/3")
          .then()
             .statusCode(200);
    }



}