package com.example.Library.Management.System.controller;

import com.example.Library.Management.System.entity.Book;
import com.example.Library.Management.System.exception.LibraryManagementException;
import com.example.Library.Management.System.model.AddLibraryRequest;
import com.example.Library.Management.System.service.LibraryService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @PostMapping("/library/createLibrary")
    @SneakyThrows
    public ResponseEntity<?> addLibrary(@RequestBody AddLibraryRequest request) {
        try{
            return ResponseEntity.ok(libraryService.addLibrary(request));
        }catch (LibraryManagementException e){
            throw e;
        }catch (Exception e){
            throw new LibraryManagementException(e.getMessage());
        }
    }
    @GetMapping("/library/getLibrary/{libId}")
    @SneakyThrows
    public ResponseEntity<?> getLibrary(@PathVariable int libId) {
        try{
            return ResponseEntity.ok(libraryService.getLibrary(libId));
        }catch (LibraryManagementException e){
            throw e;
        }catch (Exception e){
            throw new LibraryManagementException(e.getMessage());
        }
    }
}
