package com.example.Library.Management.System.service;

import com.example.Library.Management.System.entity.Book;
import com.example.Library.Management.System.entity.Department;
import com.example.Library.Management.System.exception.LibraryManagementException;
import com.example.Library.Management.System.exception.LibraryManagementExceptionUtils;
import com.example.Library.Management.System.model.AddBookRequest;
import com.example.Library.Management.System.model.AddBookResponse;
import com.example.Library.Management.System.repository.BookRepository;
import com.example.Library.Management.System.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    public AddBookResponse addBook(AddBookRequest request) throws LibraryManagementException {
        Optional<Book> bookOptional = bookRepository.findById(request.getIsbn());
        if(bookOptional.isPresent()) {
            LibraryManagementExceptionUtils.propagateBadRequestException("These Book is already present");
        }
        Optional<Department> departmentOptional = departmentRepository.findById(request.getDepartmentId());
        if(departmentOptional.isEmpty()){
            LibraryManagementExceptionUtils.propagateBadRequestException("Department is empty");
        }
        Department department = departmentOptional.get();
        Book newBook = Book.builder()
                .isbn(request.getIsbn())
                .title(request.getTitle())
                .authorName(request.getAuthorName())
                .genre(request.getGenre())
                .publicationYear(request.getPublicationYear())
                .isAvailable(request.getIsAvailable())
                .department(department)
                .build();
        newBook = bookRepository.save(newBook);

        return AddBookResponse.builder()
                .isbn(newBook.getIsbn())
                .title(newBook.getTitle())
                .authorName(newBook.getAuthorName())
                .build();
    }

    public void removeBook(String isbn) throws LibraryManagementException {
        Optional<Book> optionalBooks = bookRepository.findById(isbn);
        if(optionalBooks.isEmpty()){
            LibraryManagementExceptionUtils.propagateBadRequestException("These book is not present in library");
        }
        bookRepository.deleteById(isbn);
    }

    public List<Book> findBookByTitle(String title) throws LibraryManagementException {
        List<Book> booksList = bookRepository.findByTitleIgnoreCase(title);
        if(booksList.isEmpty()){
            LibraryManagementExceptionUtils.propagateBadRequestException("Books are not present with these title");
        }
        return booksList;
    }

    public List<Book> findBookByAuthorName(String authorName) throws LibraryManagementException {
        List<Book> booksList = bookRepository.findByAuthorNameIgnoreCase(authorName);
        if(booksList.isEmpty()){
            LibraryManagementExceptionUtils.propagateBadRequestException("Books are not present with these author name");
        }
        return booksList;
    }

    public List<Book> getAllBooks() throws LibraryManagementException {
        return bookRepository.findAll();
    }

    public List<Book> getAvailableBooks() throws LibraryManagementException {
        return bookRepository.findAvailableBooks();
    }
}
