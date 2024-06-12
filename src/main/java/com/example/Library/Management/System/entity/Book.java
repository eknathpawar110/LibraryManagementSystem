package com.example.Library.Management.System.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Entity(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    @Id
    private String isbn;
    private String title;
    private String authorName;
    private String genre;
    private String publicationYear;
    private Boolean isAvailable;
    @ManyToMany
    @JsonBackReference
    private List<Department> departments;

}
