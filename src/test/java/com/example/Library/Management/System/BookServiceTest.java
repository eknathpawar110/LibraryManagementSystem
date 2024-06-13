package com.example.Library.Management.System;

import com.example.Library.Management.System.entity.Book;
import com.example.Library.Management.System.entity.Department;
import com.example.Library.Management.System.exception.LibraryManagementException;
import com.example.Library.Management.System.model.AddBookRequest;
import com.example.Library.Management.System.model.AddBookResponse;
import com.example.Library.Management.System.repository.BookRepository;
import com.example.Library.Management.System.repository.DepartmentRepository;
import com.example.Library.Management.System.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private DepartmentRepository departmentRepository;
    @InjectMocks
    private BookService bookService;
    private Department department;
    private Book book;
    private AddBookRequest addBookRequest;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .id(2)
                .departmentName("Science")
                .build();

        book = Book.builder()
                .isbn("123")
                .title("Atomic")
                .authorName("Eknath")
                .genre("xyz")
                .publicationYear("2024")
                .isAvailable(true)
                .department(department)
                .build();
        addBookRequest = AddBookRequest.builder()
                .libraryId(1)
                .departmentId(2)
                .isbn("123")
                .title("Atomic")
                .authorName("Eknath")
                .genre("xyz")
                .publicationYear("2024")
                .isAvailable(true)
                .build();
    }

    @Test
    void testAddBook() throws LibraryManagementException {

        doReturn(Optional.empty()).when(bookRepository).findById(addBookRequest.getIsbn());
        doReturn(Optional.of(department)).when(departmentRepository).findById(addBookRequest.getDepartmentId());
        doReturn(book).when(bookRepository).save(any(Book.class));

        AddBookResponse addBookResponse = bookService.addBook(addBookRequest);

        assertEquals("123", addBookResponse.getIsbn());
        assertEquals("Atomic", addBookResponse.getTitle());
        assertEquals("Eknath", addBookResponse.getAuthorName());
    }

    @Test
    void testAddBookAlreadyPresentWithSameISBN() {
        doReturn(Optional.of(book)).when(bookRepository).findById(addBookRequest.getIsbn());
        LibraryManagementException exception = assertThrows(LibraryManagementException.class, () -> {
            bookService.addBook(addBookRequest);
        });

        assertEquals("These Book is already present", exception.getMessage());
    }
    @Test
    void testAddBookWithNonExistentDepartment() {
        doReturn(Optional.empty()).when(departmentRepository).findById(addBookRequest.getDepartmentId());
        LibraryManagementException exception = assertThrows(LibraryManagementException.class, () -> {
            bookService.addBook(addBookRequest);
        });

        assertEquals("Department is empty", exception.getMessage());
    }
    @Test
    void testRemoveBookNotFound() {
        String isbn = "123";
        doReturn(Optional.empty()).when(bookRepository).findById(isbn);
        Exception exception = assertThrows(LibraryManagementException.class, () -> {
            bookService.removeBook(isbn);
        });
        assertEquals("These book is not present in library", exception.getMessage());
    }
    @Test
    void testFindBookByTitle() throws LibraryManagementException {
        String title = "Atomic";
        List<Book> booksList = new ArrayList<>();
        booksList.add(book);
        doReturn(booksList).when(bookRepository).findByTitleIgnoreCase(title);

        List<Book> testedBooks = bookService.findBookByTitle(title);

        assertEquals(1, testedBooks.size());
        assertEquals(title, testedBooks.get(0).getTitle());
    }

    @Test
    void testFindBookByTitleNotFound() {
        String title = "Atomic";
        doReturn(new ArrayList<>()).when(bookRepository).findByTitleIgnoreCase(title);
        Exception exception = assertThrows(LibraryManagementException.class, () -> {
            bookService.findBookByTitle(title);
        });

        assertEquals("Books are not present with these title", exception.getMessage());
    }
    @Test
    void testFindBookByAuthorName() throws LibraryManagementException {
        String authorName = "Eknath";
        List<Book> booksList = new ArrayList<>();
        booksList.add(book);
        doReturn(booksList).when(bookRepository).findByAuthorNameIgnoreCase(authorName);

        List<Book> testedBooks = bookService.findBookByAuthorName(authorName);

        assertEquals(1, testedBooks.size());
        assertEquals(authorName, testedBooks.get(0).getAuthorName());
    }

    @Test
    void testFindBookByAuthorNameNotFound() {
        String authorName = "Eknath";
        doReturn(new ArrayList<>()).when(bookRepository).findByAuthorNameIgnoreCase(authorName);
        Exception exception = assertThrows(LibraryManagementException.class, () -> {
            bookService.findBookByAuthorName(authorName);
        });

        assertEquals("Books are not present with these author name", exception.getMessage());
    }
    @Test
    void testGetAllBooks() throws LibraryManagementException {
        Book book2 = Book.builder()
                .isbn("456")
                .title("Effective Java")
                .authorName("Joshua Bloch")
                .genre("Programming")
                .publicationYear("2018")
                .isAvailable(true)
                .build();

        List<Book> booksList = new ArrayList<>();
        booksList.add(book);
        booksList.add(book2);
        doReturn(booksList).when(bookRepository).findAll();

        List<Book> testedBook = bookService.getAllBooks();
        assertEquals(2, testedBook.size());
        assertEquals("Atomic", testedBook.get(0).getTitle());
        assertEquals("Effective Java", testedBook.get(1).getTitle());
    }

    @Test
    void testGetAvailableBooks() throws LibraryManagementException {
        Book book2 = Book.builder()
                .isbn("456")
                .title("Effective Java")
                .authorName("Joshua Bloch")
                .genre("Programming")
                .publicationYear("2018")
                .isAvailable(true)
                .build();

        List<Book> booksList = new ArrayList<>();
        booksList.add(book);
        booksList.add(book2);

        doReturn(booksList).when(bookRepository).findAvailableBooks();
        List<Book> result = bookService.getAvailableBooks();

        assertEquals(2, result.size());
        assertEquals("Atomic", result.get(0).getTitle());
        assertEquals("Effective Java", result.get(1).getTitle());
    }
}
