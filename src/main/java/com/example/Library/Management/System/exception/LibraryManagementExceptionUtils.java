package com.example.Library.Management.System.exception;

import com.example.Library.Management.System.util.ResponseCode;
import org.springframework.http.HttpStatus;

public final class LibraryManagementExceptionUtils {
    private LibraryManagementExceptionUtils(){
    }
    public static void propagateBadRequestException(String message, ResponseCode responseCode)
            throws LibraryManagementException {
        throw new LibraryManagementException(message, responseCode, HttpStatus.BAD_REQUEST);
    }
}
