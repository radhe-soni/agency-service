package com.agencyservice.agencyservice.exception;

public class UserExistsException extends RuntimeException {

    public UserExistsException(String msg){
        super(msg);
    }
}
