package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Data
@Builder
@JsonSerialize
@NoArgsConstructor
@AllArgsConstructor
public class UserOut {

    private Long code;
    private String message;
    private String type;
}
