package com.example.Library.Management.System.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddBookResponse {
    private String isbn;
    private String title;
    private String authorName;
}
