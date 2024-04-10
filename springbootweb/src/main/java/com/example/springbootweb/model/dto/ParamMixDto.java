package com.example.springbootweb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParamMixDto {
    private String message;
    private Integer code;
}
