package com.example.Library.Management.System.controller;

import com.example.Library.Management.System.exception.LibraryManagementException;
import com.example.Library.Management.System.model.AddDepartmentRequest;
import com.example.Library.Management.System.model.AddLibraryRequest;
import com.example.Library.Management.System.service.LibraryService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepartmentController {

    @Autowired
    private LibraryService libraryService;

    @PostMapping("/library/createDepartment")
    @SneakyThrows
    public ResponseEntity<?> addDepartment(@RequestBody AddDepartmentRequest request) {
        try{
            return ResponseEntity.ok(libraryService.addDepartment(request));
        }catch (LibraryManagementException e){
            throw e;
        }catch (Exception e){
            throw new LibraryManagementException(e.getMessage());
        }
    }
    @GetMapping("/library/getDepartment/{depId}")
    @SneakyThrows
    public ResponseEntity<?> getDepartment(@PathVariable int depId) {
        try{
            return ResponseEntity.ok(libraryService.getDepartment(depId));
        }catch (LibraryManagementException e){
            throw e;
        }catch (Exception e){
            throw new LibraryManagementException(e.getMessage());
        }
    }
}
