package com.example.Library.Management.System.service;

import com.example.Library.Management.System.entity.Book;
import com.example.Library.Management.System.entity.Department;
import com.example.Library.Management.System.entity.Library;
import com.example.Library.Management.System.exception.LibraryManagementException;
import com.example.Library.Management.System.exception.LibraryManagementExceptionUtils;
import com.example.Library.Management.System.model.AddBookRequest;
import com.example.Library.Management.System.model.AddBookResponse;
import com.example.Library.Management.System.model.AddDepartmentRequest;
import com.example.Library.Management.System.model.AddLibraryRequest;
import com.example.Library.Management.System.repository.BookRepository;
import com.example.Library.Management.System.repository.DepartmentRepository;
import com.example.Library.Management.System.repository.LibraryRepositroy;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LibraryRepositroy libraryRepositroy;
    @Autowired
    private DepartmentRepository departmentRepository;

    public String addLibrary(AddLibraryRequest request) throws LibraryManagementException {
        Library library = Library.builder()
                .libraryName(request.getLibraryName())
                .build();
        library = libraryRepositroy.save(library);
        return library.getLibraryName();
    }

    public Library getLibrary(int libId) throws LibraryManagementException {
        Optional<Library> libraryOptional = libraryRepositroy.findById(libId);
        if(libraryOptional.isEmpty()){
            LibraryManagementExceptionUtils.propagateBadRequestException("These library is not present");
        }
        return libraryOptional.get();
    }


}