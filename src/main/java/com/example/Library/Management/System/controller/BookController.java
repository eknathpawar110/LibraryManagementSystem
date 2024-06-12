package com.example.Library.Management.System.controller;

import com.example.Library.Management.System.model.AddBookRequest;
import com.example.Library.Management.System.service.LibraryService;
import com.example.Library.Management.System.exception.LibraryManagementException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    @Autowired
    private LibraryService libraryService;

    @PostMapping("/library/addBook")
    @SneakyThrows
    public ResponseEntity<?> addBook(@RequestBody AddBookRequest request) {
        try{
           return ResponseEntity.ok(libraryService.addBook(request));
        }catch (LibraryManagementException e){
            throw e;
        }catch (Exception e){
            throw new LibraryManagementException(e.getMessage());
        }
    }
    @DeleteMapping("/library/removeBook/{isbn}")
    @SneakyThrows
    public void removeBook(@PathVariable String isbn) {
        libraryService.removeBook(isbn);
    }

    @GetMapping("/library/getBookByTitle")
    @SneakyThrows
    public ResponseEntity<?> findBookByTitle(@RequestParam String title){
        return ResponseEntity.ok(libraryService.findBookByTitle(title));
    }

    @GetMapping("/library/getBookByAuthor")
    @SneakyThrows
    public ResponseEntity<?> findBookByAuthorName(@RequestParam String authorName){
        return ResponseEntity.ok(libraryService.findBookByAuthorName(authorName));
    }
    @GetMapping("/library/getAllBooks")
    @SneakyThrows
    public ResponseEntity<?> getAllBooks(){
        return ResponseEntity.ok(libraryService.getAllBooks());
    }

    @GetMapping("/library/getAvailableBooks")
    @SneakyThrows
    public ResponseEntity<?> getAvailableBooks(){
        return ResponseEntity.ok(libraryService.getAvailableBooks());
    }
}
