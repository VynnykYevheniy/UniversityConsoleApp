package com.vynnyk.universityconsole.command;

import com.vynnyk.universityconsole.service.UniversityService;
import org.springframework.stereotype.Component;

@Component
public class HeadOfDepartmentCommand implements Command {
    private final UniversityService universityService;

    public HeadOfDepartmentCommand(UniversityService universityService) {
        this.universityService = universityService;
    }

    @Override
    public void execute(String input) {
        String departmentName = input.substring(CommandType.HEAD_OF_DEPARTMENT.getCommandFormat().length()).trim();
        String headOfDepartment = universityService.getHeadOfDepartment(departmentName);
        System.out.println(headOfDepartment);
    }

    @Override
    public String getKey() {
        return CommandType.HEAD_OF_DEPARTMENT.name();
    }
}
