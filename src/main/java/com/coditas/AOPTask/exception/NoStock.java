package com.coditas.AOPTask.exception;

public class NoStock extends RuntimeException {
    public NoStock(String message) {
        super(message);
    }
}
