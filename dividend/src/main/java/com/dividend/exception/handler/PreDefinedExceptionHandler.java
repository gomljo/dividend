package com.dividend.exception.handler;

import com.dividend.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@ControllerAdvice
public class PreDefinedExceptionHandler {
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<?> handleGlobalException(Exception exception){
        log.info(exception.getClass() + " is occurred!");
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(INTERNAL_SERVER_ERROR.value())
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(
                errorResponse,
                Objects.requireNonNull(
                        HttpStatus.resolve(INTERNAL_SERVER_ERROR.value())
                )
        );
    }
}
