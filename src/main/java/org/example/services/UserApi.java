package org.example.services;

import static io.restassured.RestAssured.given;

import org.example.annotations.Path;
import org.example.dto.User;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
@Path("/user")
public class UserApi extends BaseApiAbs<UserApi> {
  private RequestSpecification rspec;
  private String login = "/login";
  private String logout = "/logout";

  public UserApi(){
    rspec = given()
          .baseUri(getUrl())
          .contentType(ContentType.JSON);
  }

  public ValidatableResponse createUser(User user) {
    return given(rspec)
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
          .basePath(login)
          .queryParam("username", user.getUsername())
          .queryParam("password", user.getPassword())
       .when()
          .get()
       .then()
          .log().all()
          .statusCode(200);
  }

  public ValidatableResponse removeUser(User user) {
    return given(rspec)
          .basePath("/{user}")
          .pathParam("user", user.getUsername())
       .when()
          .delete()
       .then()
          .log().all()
          .statusCode(200);
  }

  public ValidatableResponse logoutUser(User user) {
    return given(rspec)
          .basePath(logout)
       .when()
          .get()
       .then()
          .log().all()
          .statusCode(200);
  }

  public ValidatableResponse getUser(User user) {
    return given(rspec)
          .basePath("/{username}")
          .pathParam("username", user.getUsername())
       .when()
          .get()
       .then()
          .log().all()
          .statusCode(200);
  }
}
