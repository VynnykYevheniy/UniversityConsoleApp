package com.vynnyk.universityconsole.repository;

import com.vynnyk.universityconsole.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	Optional<Department> findByName(String name);
}

