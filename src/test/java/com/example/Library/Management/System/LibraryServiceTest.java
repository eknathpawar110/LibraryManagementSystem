package com.example.Library.Management.System;

import com.example.Library.Management.System.entity.Book;
import com.example.Library.Management.System.exception.LibraryManagementException;
import com.example.Library.Management.System.model.AddBookResponse;
import com.example.Library.Management.System.repository.BookRepository;
import com.example.Library.Management.System.service.LibraryService;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LibraryServiceTest {

//    @Mock
//    private BookRepository bookRepository;
//    @InjectMocks
//    private LibraryService libraryService;
//
//    @Test
//    void testAddBook() throws LibraryManagementException {
//
//        Book book = Book.builder()
//                .isbn("123")
//                .title("Atomic")
//                .authorName("Eknath")
//                .genre("xyz")
//                .departmentName("Mechanical")
//                .publicationYear("2024")
//                .isAvailable(true)
//                .build();
//        doReturn(Optional.empty()).when(bookRepository).findById(book.getIsbn());
//        doReturn(book).when(bookRepository).save(any());
//
//        AddBookResponse testedBook = libraryService.addBook(book);
//
//        assertEquals("123", testedBook.getIsbn());
//        assertEquals("Atomic", testedBook.getTitle());
//        assertEquals("Eknath", testedBook.getAuthorName());
//
//    }
//
//    @Test
//    void testAddBookAlreadyPresentWithSameISBN() {
//        Book book = Book.builder()
//                .isbn("123")
//                .title("Atomic")
//                .authorName("Eknath")
//                .genre("xyz")
//                .departmentName("Mechanical")
//                .publicationYear("2024")
//                .isAvailable(true)
//                .build();
//
//        doReturn(Optional.of(book)).when(bookRepository).findById(book.getIsbn());
//        Exception exception = assertThrows(LibraryManagementException.class, () -> {
//            libraryService.addBook(book);
//        });
//        assertEquals("Book with same ISBN is already present", exception.getMessage());
//    }
//    @Test
//    void testRemoveBook() throws LibraryManagementException {
//        // Arrange
//        Book book = Book.builder()
//                .isbn("123")
//                .title("Atomic")
//                .authorName("Eknath")
//                .genre("xyz")
//                .departmentName("Mechanical")
//                .publicationYear("2024")
//                .isAvailable(true)
//                .build();
//        doReturn(Optional.of(book)).when(bookRepository).findById(book.getIsbn());
//        doNothing().when(bookRepository).deleteById(book.getIsbn());
//        libraryService.removeBook(book.getIsbn());
//        verify(bookRepository, times(1)).deleteById(book.getIsbn());
//    }
//    @Test
//    void testRemoveBookNotFound() {
//        String isbn = "123";
//        doReturn(Optional.empty()).when(bookRepository).findById(isbn);
//        Exception exception = assertThrows(LibraryManagementException.class, () -> {
//            libraryService.removeBook(isbn);
//        });
//        assertEquals("These book is not present in library", exception.getMessage());
//    }
//    @Test
//    void testFindBookByTitle() throws LibraryManagementException {
//        String title = "Atomic";
//        Book book = Book.builder()
//                .isbn("123")
//                .title(title)
//                .authorName("Eknath")
//                .genre("xyz")
//                .departmentName("Mechanical")
//                .publicationYear("2024")
//                .isAvailable(true)
//                .build();
//
//        List<Book> booksList = new ArrayList<>();
//        booksList.add(book);
//        doReturn(booksList).when(bookRepository).findByTitleIgnoreCase(title);
//
//        List<Book> testedBooks = libraryService.findBookByTitle(title);
//
//        assertEquals(1, testedBooks.size());
//        assertEquals(title, testedBooks.get(0).getTitle());
//    }
//
//    @Test
//    void testFindBookByTitleNotFound() {
//        String title = "Atomic";
//        doReturn(new ArrayList<>()).when(bookRepository).findByTitleIgnoreCase(title);
//        Exception exception = assertThrows(LibraryManagementException.class, () -> {
//            libraryService.findBookByTitle(title);
//        });
//
//        assertEquals("Books are not present with these title", exception.getMessage());
//    }
//    @Test
//    void testFindBookByAuthorName() throws LibraryManagementException {
//        String authorName = "Eknath";
//        Book book = Book.builder()
//                .isbn("123")
//                .title("Atomic")
//                .authorName(authorName)
//                .genre("xyz")
//                .departmentName("Mechanical")
//                .publicationYear("2024")
//                .isAvailable(true)
//                .build();
//
//        List<Book> booksList = new ArrayList<>();
//        booksList.add(book);
//        doReturn(booksList).when(bookRepository).findByAuthorNameIgnoreCase(authorName);
//
//        List<Book> testedBooks = libraryService.findBookByAuthorName(authorName);
//
//        assertEquals(1, testedBooks.size());
//        assertEquals(authorName, testedBooks.get(0).getAuthorName());
//    }
//
//    @Test
//    void testFindBookByAuthorNameNotFound() {
//        String authorName = "Eknath";
//        doReturn(new ArrayList<>()).when(bookRepository).findByAuthorNameIgnoreCase(authorName);
//        Exception exception = assertThrows(LibraryManagementException.class, () -> {
//            libraryService.findBookByAuthorName(authorName);
//        });
//
//        assertEquals("Books are not present with these author name", exception.getMessage());
//    }
//    @Test
//    void testGetAllBooks() {
//        Book book1 = Book.builder()
//                .isbn("123")
//                .title("Atomic")
//                .authorName("Eknath")
//                .genre("xyz")
//                .departmentName("Mechanical")
//                .publicationYear("2024")
//                .isAvailable(true)
//                .build();
//
//        Book book2 = Book.builder()
//                .isbn("456")
//                .title("Effective Java")
//                .authorName("Joshua Bloch")
//                .genre("Programming")
//                .departmentName("Computer Science")
//                .publicationYear("2018")
//                .isAvailable(true)
//                .build();
//
//        List<Book> booksList = new ArrayList<>();
//        booksList.add(book1);
//        booksList.add(book2);
//        doReturn(booksList).when(bookRepository).findAll();
//
//        List<Book> testedBook = libraryService.getAllBooks();
//        assertEquals(2, testedBook.size());
//        assertEquals("Atomic", testedBook.get(0).getTitle());
//        assertEquals("Effective Java", testedBook.get(1).getTitle());
//    }
//
//    @Test
//    void testGetAvailableBooks() {
//        Book book1 = Book.builder()
//                .isbn("123")
//                .title("Atomic")
//                .authorName("Eknath")
//                .genre("xyz")
//                .departmentName("Mechanical")
//                .publicationYear("2024")
//                .isAvailable(true)
//                .build();
//
//        Book book2 = Book.builder()
//                .isbn("456")
//                .title("Effective Java")
//                .authorName("Joshua Bloch")
//                .genre("Programming")
//                .departmentName("Computer Science")
//                .publicationYear("2018")
//                .isAvailable(true)
//                .build();
//
//        List<Book> booksList = new ArrayList<>();
//        booksList.add(book1);
//        booksList.add(book2);
//
//        doReturn(booksList).when(bookRepository).findAvailableBooks();
//        List<Book> result = libraryService.getAvailableBooks();
//
//        assertEquals(2, result.size());
//        assertEquals("Atomic", result.get(0).getTitle());
//        assertEquals("Effective Java", result.get(1).getTitle());
//    }
}
