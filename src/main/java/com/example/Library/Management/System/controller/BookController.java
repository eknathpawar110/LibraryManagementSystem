package com.example.Library.Management.System.controller;

import com.example.Library.Management.System.util.ResponseUtil;
import com.example.Library.Management.System.model.AddBookRequest;
import com.example.Library.Management.System.service.BookService;
import com.example.Library.Management.System.exception.LibraryManagementException;
import com.example.Library.Management.System.util.ResponseCode;
import lombok.SneakyThrows;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/library/addBook")
    @SneakyThrows
    public ResponseEntity<?> addBook(@RequestBody @Valid AddBookRequest request) {
        try{
            return ResponseEntity.ok().body(ResponseUtil.getSuccessGenericApiResponse("Success",
                    ResponseCode.OK.name(), bookService.addBook(request)));
        }catch (LibraryManagementException e){
            throw e;
        }catch (Exception e){
            throw new LibraryManagementException(e.getMessage(), ResponseCode.INTERNAL_SERVER_ERROR,
                    HttpStatus.INTERNAL_SERVER_ERROR);
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
            throw new LibraryManagementException(e.getMessage(), ResponseCode.INTERNAL_SERVER_ERROR,
                    HttpStatus.INTERNAL_SERVER_ERROR);
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
            throw new LibraryManagementException(e.getMessage(), ResponseCode.INTERNAL_SERVER_ERROR,
                    HttpStatus.INTERNAL_SERVER_ERROR);
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
            throw new LibraryManagementException(e.getMessage(), ResponseCode.INTERNAL_SERVER_ERROR,
                    HttpStatus.INTERNAL_SERVER_ERROR);
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
            throw new LibraryManagementException(e.getMessage(), ResponseCode.INTERNAL_SERVER_ERROR,
                    HttpStatus.INTERNAL_SERVER_ERROR);
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
            throw new LibraryManagementException(e.getMessage(), ResponseCode.INTERNAL_SERVER_ERROR,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
