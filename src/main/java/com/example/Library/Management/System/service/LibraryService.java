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
import java.util.Collections;
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
    @Transactional
    public AddBookResponse addBook(AddBookRequest request) throws LibraryManagementException {
        Optional<Book> bookOptional = bookRepository.findById(request.getIsbn());
        if(bookOptional.isPresent()) {
            LibraryManagementExceptionUtils.propagateBadRequestException("These Book is already present");
        }
        Optional<Department> departmentOptional = departmentRepository.findById(request.getDepartmentId());
        if(departmentOptional.isEmpty()){
            LibraryManagementExceptionUtils.propagateBadRequestException("Department is empty");
        }
        Department department = departmentOptional.get();

        Book newBook = Book.builder()
                .isbn(request.getIsbn())
                .title(request.getTitle())
                .authorName(request.getAuthorName())
                .genre(request.getGenre())
                .publicationYear(request.getPublicationYear())
                .isAvailable(request.getIsAvailable())
                .build();

        department.getBookList().add(newBook);
        if (newBook.getDepartments() == null) {
            newBook.setDepartments(new ArrayList<>());
        }
        newBook.getDepartments().add(department);
        newBook = bookRepository.save(newBook);
        departmentRepository.save(department);

        return AddBookResponse.builder()
                .isbn(newBook.getIsbn())
                .title(newBook.getTitle())
                .authorName(newBook.getAuthorName())
                .build();
    }


    public void removeBook(String isbn) throws LibraryManagementException {
        Optional<Book> optionalBooks = bookRepository.findById(isbn);
        if(optionalBooks.isEmpty()){
            LibraryManagementExceptionUtils.propagateBadRequestException("These book is not present in library");
        }
        bookRepository.deleteById(isbn);
    }

    public List<Book> findBookByTitle(String title) throws LibraryManagementException {
        List<Book> booksList = bookRepository.findByTitleIgnoreCase(title);
        if(booksList.isEmpty()){
            LibraryManagementExceptionUtils.propagateBadRequestException("Books are not present with these title");
        }
        return booksList;
    }

    public List<Book> findBookByAuthorName(String authorName) throws LibraryManagementException {
        List<Book> booksList = bookRepository.findByAuthorNameIgnoreCase(authorName);
        if(booksList.isEmpty()){
            LibraryManagementExceptionUtils.propagateBadRequestException("Books are not present with these author name");
        }
        return booksList;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findAvailableBooks();
    }

    public String addLibrary(AddLibraryRequest request) throws LibraryManagementException {

        //Add exception
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

    public String addDepartment(AddDepartmentRequest request) throws LibraryManagementException{
        // add exception
        Optional<Library> libraryOptional = libraryRepositroy.findById(request.getLibraryId());
        if(libraryOptional.isEmpty()){
            LibraryManagementExceptionUtils.propagateBadRequestException("These library is not present");
        }

        Library library = libraryOptional.get();

        Department department = Department.builder()
                .library(library)
                .departmentName(request.getDepartmentName())
                .build();
        if (library.getDepartmentList() == null) {
            library.setDepartmentList(new ArrayList<>());
        }
        library.getDepartmentList().add(department);

        department = departmentRepository.save(department);
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
