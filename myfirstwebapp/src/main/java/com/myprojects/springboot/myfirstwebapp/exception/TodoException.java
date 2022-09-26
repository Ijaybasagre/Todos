package com.myprojects.springboot.myfirstwebapp.exception;

public class TodoException extends RuntimeException{
    public TodoException(String message) {
        super(message);
    }
}
