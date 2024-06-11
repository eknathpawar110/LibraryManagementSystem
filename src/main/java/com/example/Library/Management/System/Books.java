package com.example.Library.Management.System;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Books {
    @Id
    private String isbn;
    private String title;
    private String authorName;
    private String genre;
    private String publicationYear;
    private String departmentName;
    private boolean isAvailable;
}
