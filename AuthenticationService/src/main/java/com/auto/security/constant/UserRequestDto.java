package com.auto.security.constant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequestDto {
    private String email ;
    private String password ;
}
