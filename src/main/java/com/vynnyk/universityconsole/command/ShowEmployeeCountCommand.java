package com.vynnyk.universityconsole.command;

import com.vynnyk.universityconsole.service.UniversityService;
import org.springframework.stereotype.Component;

@Component
public class ShowEmployeeCountCommand implements Command {
	private final UniversityService universityService;

	public ShowEmployeeCountCommand(UniversityService universityService) {
		this.universityService = universityService;
	}

	@Override
	public void execute(String input) {
		String departmentName = input.substring(CommandType.COUNT_EMPLOYEES.getCommandFormat().length()).trim().replaceAll("\\.$", "");
		String employeeCount = universityService.getEmployeeCount(departmentName);
		System.out.println(employeeCount);
	}

	@Override
	public String getKey() {
		return CommandType.COUNT_EMPLOYEES.name();
	}
}
