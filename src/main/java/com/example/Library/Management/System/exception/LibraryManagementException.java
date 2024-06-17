package com.example.Library.Management.System.exception;

import com.example.Library.Management.System.util.ResponseCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public class LibraryManagementException extends Exception{
    @Getter
    private final ResponseCode responseCode;
    private final HttpStatus statusCode;
    public LibraryManagementException(String message, ResponseCode responseCode, HttpStatus statusCode) {
        super(message);
        this.responseCode = responseCode;
        this.statusCode = statusCode;
    }
    public final HttpStatus getStatusCode() {
        return statusCode;
    }
}
