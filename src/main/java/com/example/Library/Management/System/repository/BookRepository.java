package com.example.Library.Management.System.repository;

import com.example.Library.Management.System.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByTitleIgnoreCase(String title);

    List<Book> findByAuthorNameIgnoreCase(String author);
    @Query("select b from books b where b.isAvailable = true")
    List<Book> findAvailableBooks();
}
