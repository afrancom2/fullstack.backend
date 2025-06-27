package com.sysman.fullstack.backend.util.exception;

public class PercentageException extends RuntimeException {
    public PercentageException(String message) {
        super(String.format("NotFound percentage exception {}", message));
    }
}