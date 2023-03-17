package com.youtube.todoApp.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class TodoExeptions extends RuntimeException{

    private String message;
    private HttpStatus httpStatus;

    public TodoExeptions(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
