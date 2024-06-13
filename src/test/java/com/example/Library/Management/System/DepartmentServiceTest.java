package com.example.Library.Management.System;

import com.example.Library.Management.System.entity.Department;
import com.example.Library.Management.System.entity.Library;
import com.example.Library.Management.System.exception.LibraryManagementException;
import com.example.Library.Management.System.model.AddDepartmentRequest;
import com.example.Library.Management.System.repository.DepartmentRepository;
import com.example.Library.Management.System.repository.LibraryRepositroy;
import com.example.Library.Management.System.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest{

    @Mock
    private LibraryRepositroy libraryRepositroy;

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    private AddDepartmentRequest addDepartmentRequest;
    private Library library;
    private Department department;

    @BeforeEach
    void setUp() {
        addDepartmentRequest = AddDepartmentRequest.builder()
                .libraryId(1)
                .departmentName("Science")
                .build();

        library = Library.builder()
                .id(1)
                .libraryName("Central Library")
                .build();

        department = Department.builder()
                .library(library)
                .departmentName("Science")
                .build();
    }

    @Test
    void testAddDepartment() throws LibraryManagementException {
        doReturn(Optional.of(library)).when(libraryRepositroy).findById(addDepartmentRequest.getLibraryId());
        doReturn(department).when(departmentRepository).save(any(Department.class));
        doReturn(library).when(libraryRepositroy).save(any(Library.class));

        String departmentName = departmentService.addDepartment(addDepartmentRequest);

        assertEquals("Science", departmentName);
    }

    @Test
    void testAddDepartmentLibraryNotFound() {
        doReturn(Optional.empty()).when(libraryRepositroy).findById(addDepartmentRequest.getLibraryId());

        LibraryManagementException exception = assertThrows(LibraryManagementException.class, () -> {
            departmentService.addDepartment(addDepartmentRequest);
        });

        assertEquals("These library is not present", exception.getMessage());
    }
}
