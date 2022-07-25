package org.example.services;

import org.example.dto.User;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;


public class UserApi {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String PATH = "/user";
    private RequestSpecification rspec;

    public UserApi(){
        rspec = given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON);
    }

    public ValidatableResponse createUser (User user) {
        return given(rspec)
                    .basePath(PATH)
                    .body(user)
                    .log().all()
                .when()
                    .post()
                .then()
                    .log().all()
                    .statusCode(200);
    }

    public ValidatableResponse loginUser(User user) {
        return given(rspec)
                .basePath(PATH + "/login")
                .queryParam("username", user.getUsername())
                .queryParam( "password", user.getPassword())
             .when()
                .get()
             .then()
                .log().all()
                .statusCode(200);
    }

    public ValidatableResponse removeUser(User user) {
        return given(rspec)
                .basePath(PATH + "/{user}")
                .pathParam("user", user.getUsername())
             .when()
                .delete()
             .then()
                .log().all()
                .statusCode(200);
    }

    public ValidatableResponse logoutUser(User user) {
        return given(rspec)
                .basePath(PATH + "/logout")
             .when()
                .get()
             .then()
                .log().all()
                .statusCode(200);
    }

    public ValidatableResponse getUser(User user) {
        return given(rspec)
                .basePath(PATH + "/{username}")
                .pathParam("username", user.getUsername())
             .when()
                .get()
             .then()
                .log().all()
                .statusCode(200);
    }
}
