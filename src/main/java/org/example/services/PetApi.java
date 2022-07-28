package org.example.services;

import static io.restassured.RestAssured.given;

import org.example.annotations.Path;
import org.example.dto.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

@Path("/pet")
public class PetApi extends BaseApiAbs<PetApi> {
  private RequestSpecification rspec;

  public PetApi() {
    rspec = given()
            .baseUri(getUrl());
  }

  public ValidatableResponse createPet(Pet pet) {
    return given(rspec)
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
              .basePath("/{petId}")
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
              .basePath("/{petId}")
              .accept("application/json")
              .contentType("application/x-www-form-urlencoded")
              .pathParam("petId", pet.getId())
          .when()
              .delete()
          .then()
              .log().all()
              .statusCode(200);
  }

  public  ValidatableResponse setUnavailablePetStatus(Pet pet) {
    return given(rspec)
              .basePath("/{petId}")
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
