package com.auto.entity.Shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {
    private String toEmail;
    private String subject;
    private String code;
    private String ref ;
}
