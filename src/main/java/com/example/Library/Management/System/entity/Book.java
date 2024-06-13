package com.example.Library.Management.System.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Department department;

}
