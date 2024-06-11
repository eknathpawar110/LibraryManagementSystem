package com.example.Library.Management.System;

import com.example.Library.Management.System.exception.LibraryManagementException;
import com.example.Library.Management.System.exception.LibraryManagementExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryService {
    @Autowired
    private BookRepository bookRepository;
    public void addBook(Books book) throws LibraryManagementException {
        Optional<Books> optionalBooks = bookRepository.findById(book.getIsbn());
        if(optionalBooks.isPresent()){
            LibraryManagementExceptionUtils.propagateBadRequestException("Book with same ISBN is already present");
        }
        Books books = Books.builder()
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .authorName(book.getAuthorName())
                .departmentName(book.getDepartmentName())
                .genre(book.getGenre())
                .publicationYear(book.getPublicationYear())
                .isAvailable(book.isAvailable())
                .build();
        bookRepository.save(books);
    }


    public void removeBook(String isbn) throws LibraryManagementException {
        Optional<Books> optionalBooks = bookRepository.findById(isbn);
        if(optionalBooks.isEmpty()){
            LibraryManagementExceptionUtils.propagateBadRequestException("These book is not present in library");
        }
        bookRepository.deleteById(isbn);
    }

    public List<Books> findBookByTitle(String title) throws LibraryManagementException {
        List<Books> booksList = bookRepository.findByTitleIgnoreCase(title);
        if(booksList.isEmpty()){
            LibraryManagementExceptionUtils.propagateBadRequestException("Books are not present with these title");
        }
        return booksList;
    }

    public List<Books> findBookByAuthorName(String authorName) throws LibraryManagementException {
        List<Books> booksList = bookRepository.findByAuthorNameIgnoreCase(authorName);
        if(booksList.isEmpty()){
            LibraryManagementExceptionUtils.propagateBadRequestException("Books are not present with these author name");
        }
        return booksList;
    }

    public List<Books> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Books> getAvailableBooks() {
        return bookRepository.findAll().stream()
                .filter(Books::isAvailable)
                .collect(Collectors.toList());
        //return bookRepository.findAvailableBooks();
    }
}
