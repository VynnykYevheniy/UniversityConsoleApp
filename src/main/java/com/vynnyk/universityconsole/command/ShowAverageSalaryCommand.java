package com.vynnyk.universityconsole.command;

import com.vynnyk.universityconsole.service.UniversityService;
import org.springframework.stereotype.Component;

@Component
public class ShowAverageSalaryCommand implements Command {
	private final UniversityService universityService;

	public ShowAverageSalaryCommand(UniversityService universityService) {
		this.universityService = universityService;
	}

	@Override
	public void execute(String input) {
		String departmentName = input.substring(CommandType.AVERAGE_SALARY.getCommandFormat().length()).trim().replaceAll("\\.$", "");
		String averageSalary = universityService.getAverageSalary(departmentName);
		System.out.println(averageSalary);
	}

	@Override
	public String getKey() {
		return CommandType.AVERAGE_SALARY.name();
	}
}
