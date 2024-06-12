package com.example.Library.Management.System.repository;

import com.example.Library.Management.System.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
