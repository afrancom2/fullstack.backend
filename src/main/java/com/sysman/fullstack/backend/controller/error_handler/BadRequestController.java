package com.sysman.fullstack.backend.controller.error_handler;

import com.sysman.fullstack.backend.model.response.error.BaseErrorResponse;
import com.sysman.fullstack.backend.model.response.error.ErrorResponse;
import com.sysman.fullstack.backend.util.exception.ForbiddenException;
import com.sysman.fullstack.backend.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@RequiredArgsConstructor
public class BadRequestController {

    @ExceptionHandler({ForbiddenException.class})
    public BaseErrorResponse handleForbidden(RuntimeException exception) {
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .status(HttpStatus.FORBIDDEN.name())
                .code(HttpStatus.FORBIDDEN.value())
                .build();
    }

    @ExceptionHandler({NotFoundException.class})
    public BaseErrorResponse handleNotFound(RuntimeException exception) {
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND.name())
                .code(HttpStatus.NOT_FOUND.value())
                .build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseErrorResponse handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String name = ex.getName(); // nombre del parámetro
        String value = String.valueOf(ex.getValue()); // valor recibido
        String expectedType = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "unknown";

        return ErrorResponse.builder()
                .message(String.format("Invalid value '%s' for parameter '%s'. Expected type: %s", value, name, expectedType))
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }
}
