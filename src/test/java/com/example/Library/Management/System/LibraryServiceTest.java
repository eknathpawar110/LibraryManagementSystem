package com.example.Library.Management.System;

import com.example.Library.Management.System.entity.Library;
import com.example.Library.Management.System.exception.LibraryManagementException;
import com.example.Library.Management.System.model.AddLibraryRequest;
import com.example.Library.Management.System.repository.LibraryRepositroy;
import com.example.Library.Management.System.service.LibraryService;
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
class LibraryServiceTest {

    @Mock
    private LibraryRepositroy libraryRepositroy;

    @InjectMocks
    private LibraryService libraryService;

    private AddLibraryRequest addLibraryRequest;
    private Library library;

    @BeforeEach
    void setUp() {
        addLibraryRequest = AddLibraryRequest.builder()
                .libraryName("Central Library")
                .build();

        library = Library.builder()
                .libraryName("Central Library")
                .build();
    }

    @Test
    void testAddLibrary() throws LibraryManagementException {
        doReturn(library).when(libraryRepositroy).save(any(Library.class));

        String libraryName = libraryService.addLibrary(addLibraryRequest);

        assertEquals("Central Library", libraryName);
    }
    @Test
    void testGetLibrary() throws LibraryManagementException {
        doReturn(Optional.of(library)).when(libraryRepositroy).findById(1);

        Library foundLibrary = libraryService.getLibrary(1);

        assertEquals("Central Library", foundLibrary.getLibraryName());
    }

    @Test
    void testGetLibraryNotFound() {
        doReturn(Optional.empty()).when(libraryRepositroy).findById(1);

        LibraryManagementException exception = assertThrows(LibraryManagementException.class, () -> {
            libraryService.getLibrary(1);
        });

        assertEquals("These library is not present", exception.getMessage());
    }
}
