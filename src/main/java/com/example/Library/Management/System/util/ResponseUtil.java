package com.example.Library.Management.System.util;

import com.example.Library.Management.System.util.GenericApiResponse;

public class ResponseUtil {
    public static GenericApiResponse getSuccessGenericApiResponse(String message, String responseCode, Object data) {

        return GenericApiResponse.builder().message(message).status("success")
                .responseCode(responseCode)
                .data(data).build();

    }
    public static GenericApiResponse getErrorGenericApiResponse(String message, String responseCode) {

        return GenericApiResponse.builder().message(message).status("error")
                .responseCode(responseCode).build();

    }
}
