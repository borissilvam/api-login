package com.viamatica.login.web.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler{

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ApiError> handleUserNotFoundException(UserNotFound ex){
        ApiError apiError = new  ApiError(HttpStatus.NOT_FOUND, ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
       // return super.handleExceptionInternal(ex, body, headers, statusCode, request);

        ApiError apiError = new ApiError((HttpStatus) statusCode, ex.getMessage());

        return ResponseEntity.status(statusCode).headers(headers).body(apiError);
    }
}
