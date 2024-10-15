package com.vynnyk.universityconsole.service;

public interface UniversityService {
	String getHeadOfDepartment(String departmentName);

	String getDepartmentStatistics(String departmentName);

	String getAverageSalary(String departmentName);

	String getEmployeeCount(String departmentName);

	String globalSearch(String template);
}
