package com.auto.security.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponseDto {
    private long id ;
    private String role ;
    private String jwt ;
}
