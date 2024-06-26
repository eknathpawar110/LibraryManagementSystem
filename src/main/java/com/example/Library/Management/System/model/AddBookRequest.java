package com.example.Library.Management.System.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddBookRequest {
    private int libraryId;
    private int departmentId;
    private String isbn;
    private String title;
    private String authorName;
    private String genre;
    private String publicationYear;
    private Boolean isAvailable;
}
