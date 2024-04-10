package com.example.springbooterror.model.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {

    private String message;
    private String error;
    private int status;
    private LocalDate date;
}
