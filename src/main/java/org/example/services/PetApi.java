package org.example.services;

import org.example.dto.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.dto.User;

import static io.restassured.RestAssured.given;

public class PetApi {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String PATH = "/pet";
    private RequestSpecification rspec;

    public PetApi(){
        rspec = given()
                .baseUri(BASE_URL);
    }

    public ValidatableResponse createPet (Pet pet) {
        return given(rspec)
                    .basePath(PATH)
                    .accept("application/json")
                    .contentType(ContentType.JSON)
                    .body(pet)
                    .log().all()
                .when()
                    .post()
                .then()
                    .log().all()
                    .statusCode(200);
    }

    public ValidatableResponse getPet(Pet pet) {
        return given(rspec)
                    .basePath(PATH + "/{petId}")
                    .pathParam("petId", pet.getId())
                    .accept("application/json")
                .when()
                    .get()
                .then()
                    .log().all()
                    .statusCode(200);
    }

    public ValidatableResponse removePet(Pet pet) {
        return given(rspec)
                    .basePath(PATH + "/{petId}")
                    .accept("application/json")
                    .contentType("application/x-www-form-urlencoded")
                    .pathParam("petId", pet.getId())
                .when()
                    .delete()
                .then()
                    .log().all()
                    .statusCode(200);
    }

    public  ValidatableResponse setUnavailablePetStatus (Pet pet) {
        return given(rspec)
                    .basePath(PATH + "/{petId}")
                    .contentType("application/x-www-form-urlencoded")
                    .pathParam("petId", pet.getId())
                    .body("status=unavailable")
                .when()
                    .post()
                .then()
                    .log().all()
                    .statusCode(200);
    }
}
