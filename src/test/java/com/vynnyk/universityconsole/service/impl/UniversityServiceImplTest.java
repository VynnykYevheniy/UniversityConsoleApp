package com.vynnyk.universityconsole.service.impl;

import com.vynnyk.universityconsole.exception.ResourceNotFoundException;
import com.vynnyk.universityconsole.model.Department;
import com.vynnyk.universityconsole.model.Lector;
import com.vynnyk.universityconsole.model.enumetarion.Degree;
import com.vynnyk.universityconsole.repository.DepartmentRepository;
import com.vynnyk.universityconsole.repository.LectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class UniversityServiceImplTest {

	@InjectMocks
	private UniversityServiceImpl universityService;

	@Mock
	private DepartmentRepository departmentRepository;

	@Mock
	private LectorRepository lectorRepository;

	private Department department;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		Lector head = new Lector();
		head.setName("Dr. Smith");
		head.setDegree(Degree.PROFESSOR);
		head.setSalary(80000);

		Lector lector1 = new Lector();
		lector1.setName("Alice");
		lector1.setDegree(Degree.ASSISTANT);
		lector1.setSalary(50000);

		Lector lector2 = new Lector();
		lector2.setName("Bob");
		lector2.setDegree(Degree.ASSOCIATE_PROFESSOR);
		lector2.setSalary(60000);

		department = new Department();
		department.setName("Computer Science");
		department.setHeadOfDepartment(head);
		department.setLectors(Arrays.asList(head, lector1, lector2));
	}

	@Test
	void testGetHeadOfDepartment() {
		when(departmentRepository.findByName("Computer Science")).thenReturn(Optional.of(department));

		String result = universityService.getHeadOfDepartment("Computer Science");

		assertEquals("Head of Computer Science department is Dr. Smith", result);
	}

	@Test
	void testGetDepartmentStatistics() {
		when(departmentRepository.findByName("Computer Science")).thenReturn(Optional.of(department));

		String result = universityService.getDepartmentStatistics("Computer Science");

		assertEquals("assistants - 1, associate professors - 1, professors - 1", result);
	}

	@Test
	void testGetAverageSalary() {
		when(departmentRepository.findByName("Computer Science")).thenReturn(Optional.of(department));

		String result = universityService.getAverageSalary("Computer Science");

		assertEquals("The average salary of Computer Science is 63333.33", result);
	}

	@Test
	void testGetEmployeeCount() {
		when(departmentRepository.findByName("Computer Science")).thenReturn(Optional.of(department));

		String result = universityService.getEmployeeCount("Computer Science");

		assertEquals("Employee count: 3", result);
	}

	@Test
	void testGetHeadOfDepartmentNotFound() {
		when(departmentRepository.findByName("Mathematics")).thenReturn(Optional.empty());

		ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () ->
				universityService.getHeadOfDepartment("Mathematics")
		);

		assertEquals("Department Mathematics not found", thrown.getMessage());
	}

	@Test
	void testGlobalSearch() {
		Lector lector1 = new Lector();
		lector1.setName("Alice");

		Lector lector2 = new Lector();
		lector2.setName("Bob");

		when(lectorRepository.findByNameContaining("A")).thenReturn(Arrays.asList(lector1, lector2));

		String result = universityService.globalSearch("A");

		assertEquals("Alice, Bob", result);
	}

	@Test
	void testGlobalSearchNoResults() {
		when(lectorRepository.findByNameContaining("Z")).thenReturn(Collections.emptyList());

		String result = universityService.globalSearch("Z");

		assertEquals("", result);
	}
}