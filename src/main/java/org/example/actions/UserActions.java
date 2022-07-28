package org.example.actions;

import static org.hamcrest.Matchers.equalTo;

import io.restassured.response.ValidatableResponse;
import org.example.dto.User;
import org.example.dto.UserOut;
import org.example.services.UserApi;
import org.junit.jupiter.api.Assertions;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserActions {

  private final UserApi userApi;
  private final User user;

  public UserActions(User user) {
    this.user = user;
    userApi = new UserApi();
  }

  public UserActions createUser() {
    ValidatableResponse response = userApi.createUser(user)
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/user/CreateUser.json"));
    UserOut userOut = response.extract().body().as(UserOut.class);
    Assertions.assertEquals("122", userOut.getMessage());
    return this;
  }

  public UserActions loginUser(){
    userApi.loginUser(user);
    return this;
  }
  public UserActions logoutUser(){
    userApi.logoutUser(user);
    return this;
  }
  public UserActions removeUser(){
    userApi.removeUser(user);
    return this;
  }

  public UserActions getUser() {
    userApi.getUser(user)
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/user/GetUser.json"))
            .body("username", equalTo(user.getUsername()));
    return this;
  }
}
