
package org.example.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;
@Data
@Builder
@AllArgsConstructor
public class User {

    private String email;
    private String firstName;
    private Long id;
    private String lastName;
    private String password;
    private String phone;
    private Long userStatus;
    private String username;
}
