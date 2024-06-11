package com.example.Library.Management.System;

import com.example.Library.Management.System.exception.LibraryManagementException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @PostMapping("/addBook")
    @SneakyThrows
    public void addBook(@RequestBody Books book) {
        try{
            libraryService.addBook(book);
        }catch (LibraryManagementException e){
            throw e;
        }catch (Exception e){
            throw new LibraryManagementException(e.getMessage());
        }
    }
    @DeleteMapping("/removeBook/{isbn}")
    public void removeBook(@PathVariable String isbn) throws LibraryManagementException {
        libraryService.removeBook(isbn);
    }

    @GetMapping("/getBookByTitle")
    public List<Books> findBookByTitle(@RequestParam String title) throws LibraryManagementException {
        return libraryService.findBookByTitle(title);
    }

    @GetMapping("/getBookByAuthor")
    public List<Books> findBookByAuthorName(@RequestParam String authorName) throws LibraryManagementException {
        return libraryService.findBookByAuthorName(authorName);
    }
    @GetMapping("/getAllBooks")
    public List<Books> getAllBooks() throws LibraryManagementException {
        return libraryService.getAllBooks();
    }

    @GetMapping("/getAvailableBooks")
    public List<Books> getAvailableBooks() throws LibraryManagementException {
        return libraryService.getAvailableBooks();
    }
}
