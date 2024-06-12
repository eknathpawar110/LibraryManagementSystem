package com.example.Library.Management.System.repository;

import com.example.Library.Management.System.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepositroy extends JpaRepository<Library, Integer> {
}
