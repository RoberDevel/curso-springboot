package com.example.springbooterror.exceptions;

import lombok.NoArgsConstructor;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

}
