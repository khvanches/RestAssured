package org.example;

import org.example.actions.UserActions;
import org.example.dto.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class UserTest {


  private UserActions userActions;
  private User user;

  @BeforeEach
  private void tearUp(){
    user = User.builder()
            .userStatus(11L)
            .email("we@we.com")
            .id(122L)
            .firstName("first")
            .lastName("last")
            .password("password")
            .username("user")
            .phone("phone")
            .build();
    userActions = new UserActions(user);
  }

  @AfterEach
  private void tearDown(){
    userActions.removeUser();
  }



  /* Test Case #1
  Summary: Login New User
  Precondition:
      - Create a new user
  Steps:
      1) Login the user
      2) Logout the user
  Expected result:
      - User should be successfully logged in. Status code 200 OK
      - User should be successfully logged out. Status code 200 OK
  Post processing:
      - Remove the user
  * */
  @Test
  public void loginUser() {

    userActions.createUser()
            .loginUser()
            .logoutUser();
  }

  /* Test Case #2
  Summary: Get user by name
  Precondition:
      - Create a new user
  Steps:
      1) Get user by name
  Expected result:
      - We have the same user's name. Status code 200 OK
  Post processing:
      - Remove the user
  * */
  @Test
  public void getUser() {

    userActions.createUser()
            .getUser();
  }
}
