package com.sysman.fullstack.backend.util.exception;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException() {
        super("Forbidden request");
    }
}
