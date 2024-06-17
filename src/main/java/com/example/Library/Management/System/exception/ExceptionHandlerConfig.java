package com.example.Library.Management.System.exception;

import com.example.Library.Management.System.util.ResponseCode;
import com.example.Library.Management.System.util.ResponseUtil;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class ExceptionHandlerConfig {
    @ExceptionHandler(LibraryManagementException.class)
    public final ResponseEntity<?> apiCustomExceptionHandle(LibraryManagementException customException){
        return new ResponseEntity<>(
                ResponseUtil.getErrorGenericApiResponse(customException.getMessage(),
                        customException.getResponseCode().name()),
                customException.getStatusCode()
        );
    }
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public final ResponseEntity<?> handleException(MethodArgumentNotValidException exception){
        String message = Objects.nonNull(exception) && Objects.nonNull(exception.getBindingResult().getFieldError())
                ? exception.getBindingResult().getFieldError().getDefaultMessage()
                : "Bad Request";
        return new ResponseEntity<>(ResponseUtil.getErrorGenericApiResponse(message, ResponseCode.Bad_Request.name()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ InvalidFormatException.class,
            HttpMessageNotReadableException.class, MissingServletRequestParameterException.class })
    public final ResponseEntity<?> handleException(Exception exception) {
        String message = Objects.nonNull(exception) && Objects.nonNull(exception.getMessage()) ? exception.getMessage()
                : "Bad Request";
        return new ResponseEntity<>(ResponseUtil.getErrorGenericApiResponse(message, ResponseCode.Bad_Request.name()),
                HttpStatus.BAD_REQUEST);
    }
}
