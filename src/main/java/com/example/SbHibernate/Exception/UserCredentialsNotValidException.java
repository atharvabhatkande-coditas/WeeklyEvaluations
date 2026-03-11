package com.example.SbHibernate.Exception;

public class UserCredentialsNotValidException extends RuntimeException {
    public UserCredentialsNotValidException(String message) {
        super(message);
    }
}
