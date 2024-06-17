package com.example.Library.Management.System.controller;

import com.example.Library.Management.System.exception.LibraryManagementException;
import com.example.Library.Management.System.model.AddDepartmentRequest;
import com.example.Library.Management.System.service.DepartmentService;
import com.example.Library.Management.System.util.ResponseCode;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/library/createDepartment")
    @SneakyThrows
    public ResponseEntity<?> addDepartment(@RequestBody AddDepartmentRequest request) {
        try{
            return ResponseEntity.ok(departmentService.addDepartment(request));
        }catch (LibraryManagementException e){
            throw e;
        }catch (Exception e){
            throw new LibraryManagementException(e.getMessage(), ResponseCode.INTERNAL_SERVER_ERROR,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/library/getDepartment/{depId}")
    @SneakyThrows
    public ResponseEntity<?> getDepartment(@PathVariable int depId) {
        try{
            return ResponseEntity.ok(departmentService.getDepartment(depId));
        }catch (LibraryManagementException e){
            throw e;
        }catch (Exception e){
            throw new LibraryManagementException(e.getMessage(), ResponseCode.INTERNAL_SERVER_ERROR,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
