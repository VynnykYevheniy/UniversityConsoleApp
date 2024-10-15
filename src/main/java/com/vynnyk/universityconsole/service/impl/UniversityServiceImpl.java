package com.vynnyk.universityconsole.service.impl;

import com.vynnyk.universityconsole.exception.ResourceNotFoundException;
import com.vynnyk.universityconsole.model.Department;
import com.vynnyk.universityconsole.model.Lector;
import com.vynnyk.universityconsole.model.enumetarion.Degree;
import com.vynnyk.universityconsole.repository.DepartmentRepository;
import com.vynnyk.universityconsole.repository.LectorRepository;
import com.vynnyk.universityconsole.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UniversityServiceImpl implements UniversityService {

	private static final String DEPARTMENT_NOT_FOUND = "Department %s not found";
	private static final String HEAD_OF_DEPARTMENT = "Head of %s department is %s";
	private static final String DEPARTMENT_STATISTICS = "assistants - %d,\n associate professors - %d,\n professors - %d";
	private static final String AVERAGE_SALARY = "The average salary of %s is %.2f";
	private static final String EMPLOYEE_COUNT = "Employee count: %d";

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private LectorRepository lectorRepository;

	@Override
	public String getHeadOfDepartment(String departmentName) {
		Department department = departmentRepository.findByName(departmentName)
				.orElseThrow(() -> new ResourceNotFoundException(String.format(DEPARTMENT_NOT_FOUND, departmentName)));
		return String.format(HEAD_OF_DEPARTMENT, departmentName, department.getHeadOfDepartment().getName());
	}

	@Override
	public String getDepartmentStatistics(String departmentName) {
		Department department = departmentRepository.findByName(departmentName)
				.orElseThrow(() -> new ResourceNotFoundException(String.format(DEPARTMENT_NOT_FOUND, departmentName)));
		long assistantCount = department.getLectors().stream()
				.filter(lector -> lector.getDegree() == Degree.ASSISTANT).count();
		long associateProfessorCount = department.getLectors().stream()
				.filter(lector -> lector.getDegree() == Degree.ASSOCIATE_PROFESSOR).count();
		long professorCount = department.getLectors().stream()
				.filter(lector -> lector.getDegree() == Degree.PROFESSOR).count();

		return String.format(DEPARTMENT_STATISTICS, assistantCount, associateProfessorCount, professorCount);
	}

	@Override
	public String getAverageSalary(String departmentName) {
		Department department = departmentRepository.findByName(departmentName)
				.orElseThrow(() -> new ResourceNotFoundException(String.format(DEPARTMENT_NOT_FOUND, departmentName)));
		double averageSalary = department.getLectors().stream()
				.mapToDouble(Lector::getSalary)
				.average().orElse(0);

		return String.format(AVERAGE_SALARY, departmentName, averageSalary);
	}

	@Override
	public String getEmployeeCount(String departmentName) {
		Department department = departmentRepository.findByName(departmentName)
				.orElseThrow(() -> new ResourceNotFoundException(String.format(DEPARTMENT_NOT_FOUND, departmentName)));
		return String.format(EMPLOYEE_COUNT, department.getLectors().size());
	}

	@Override
	public String globalSearch(String template) {
		List<Lector> lectors = lectorRepository.findByNameContaining(template);
		return lectors.stream().map(Lector::getName).collect(Collectors.joining(", "));
	}
}