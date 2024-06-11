package com.example.Library.Management.System;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Books, String> {
    List<Books> findByTitleIgnoreCase(String title);

    List<Books> findByAuthorNameIgnoreCase(String author);
    @Query("SELECT b FROM Books b WHERE b.isAvailable = true")
    List<Books> findAvailableBooks();
}
