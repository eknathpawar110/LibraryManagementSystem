package com.example.Library.Management.System.service;

import com.example.Library.Management.System.entity.Department;
import com.example.Library.Management.System.entity.Library;
import com.example.Library.Management.System.exception.LibraryManagementException;
import com.example.Library.Management.System.exception.LibraryManagementExceptionUtils;
import com.example.Library.Management.System.model.AddDepartmentRequest;
import com.example.Library.Management.System.repository.DepartmentRepository;
import com.example.Library.Management.System.repository.LibraryRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private LibraryRepositroy libraryRepositroy;
    @Autowired
    private DepartmentRepository departmentRepository;
    public String addDepartment(AddDepartmentRequest request) throws LibraryManagementException {
        Optional<Library> libraryOptional = libraryRepositroy.findById(request.getLibraryId());
        if(libraryOptional.isEmpty()){
            LibraryManagementExceptionUtils.propagateBadRequestException("These library is not present");
        }
        Library library = libraryOptional.get();
        Department department = Department.builder()
                .library(library)
                .departmentName(request.getDepartmentName())
                .build();
        department = departmentRepository.save(department);
        if (library.getDepartmentList() == null) {
            library.setDepartmentList(new ArrayList<>());
        }
        library.getDepartmentList().add(department);
        libraryRepositroy.save(library);
        return department.getDepartmentName();
    }

    public Department getDepartment(int depId) throws LibraryManagementException {
        Optional<Department> departmentOptional = departmentRepository.findById(depId);
        if(departmentOptional.isEmpty()){
            LibraryManagementExceptionUtils.propagateBadRequestException("These department is not present");
        }
        return departmentOptional.get();
    }
}
