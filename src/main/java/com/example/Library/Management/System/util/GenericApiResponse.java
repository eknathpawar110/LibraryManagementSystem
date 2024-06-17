package com.example.Library.Management.System.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericApiResponse {
    private String status;
    private String message;
    private String responseCode;
    private Object data;

    public GenericApiResponse(String message, String responseCode) {
        this.message = message;
        this.responseCode = responseCode;
    }
}
