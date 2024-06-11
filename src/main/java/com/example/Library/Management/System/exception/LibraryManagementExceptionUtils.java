package com.example.Library.Management.System.exception;

public final class LibraryManagementExceptionUtils {
    private LibraryManagementExceptionUtils(){

    }
    public static void propagateBadRequestException(String message)
            throws LibraryManagementException {
        throw new LibraryManagementException(message);
    }
}
