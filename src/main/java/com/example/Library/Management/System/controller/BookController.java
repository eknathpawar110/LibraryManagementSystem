package com.example.Library.Management.System.controller;

import com.example.Library.Management.System.model.AddBookRequest;
import com.example.Library.Management.System.service.BookService;
import com.example.Library.Management.System.service.LibraryService;
import com.example.Library.Management.System.exception.LibraryManagementException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/library/addBook")
    @SneakyThrows
    public ResponseEntity<?> addBook(@RequestBody AddBookRequest request) {
        try{
           return ResponseEntity.ok(bookService.addBook(request));
        }catch (LibraryManagementException e){
            throw e;
        }catch (Exception e){
            throw new LibraryManagementException(e.getMessage());
        }
    }
    @DeleteMapping("/library/removeBook/{isbn}")
    @SneakyThrows
    public void removeBook(@PathVariable String isbn) {
        try{
            bookService.removeBook(isbn);
        }catch (LibraryManagementException e){
            throw e;
        }catch (Exception e){
            throw new LibraryManagementException(e.getMessage());
        }
    }

    @GetMapping("/library/getBookByTitle")
    @SneakyThrows
    public ResponseEntity<?> findBookByTitle(@RequestParam String title){
        try{
            return ResponseEntity.ok(bookService.findBookByTitle(title));
        }catch (LibraryManagementException e){
            throw e;
        }catch (Exception e){
            throw new LibraryManagementException(e.getMessage());
        }
    }

    @GetMapping("/library/getBookByAuthor")
    @SneakyThrows
    public ResponseEntity<?> findBookByAuthorName(@RequestParam String authorName){
        try{
            return ResponseEntity.ok(bookService.findBookByAuthorName(authorName));
        }catch (LibraryManagementException e){
            throw e;
        }catch (Exception e){
            throw new LibraryManagementException(e.getMessage());
        }
    }
    @GetMapping("/library/getAllBooks")
    @SneakyThrows
    public ResponseEntity<?> getAllBooks(){
        try{
            return ResponseEntity.ok(bookService.getAllBooks());
        }catch (LibraryManagementException e){
            throw e;
        }catch (Exception e){
            throw new LibraryManagementException(e.getMessage());
        }
    }

    @GetMapping("/library/getAvailableBooks")
    @SneakyThrows
    public ResponseEntity<?> getAvailableBooks(){
        try{
            return ResponseEntity.ok(bookService.getAvailableBooks());
        }catch (LibraryManagementException e){
            throw e;
        }catch (Exception e){
            throw new LibraryManagementException(e.getMessage());
        }
    }
}
