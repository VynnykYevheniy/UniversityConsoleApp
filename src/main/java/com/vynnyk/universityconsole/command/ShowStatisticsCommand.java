package com.vynnyk.universityconsole.command;

import com.vynnyk.universityconsole.service.UniversityService;
import org.springframework.stereotype.Component;

@Component
public class ShowStatisticsCommand implements Command {
    private final UniversityService universityService;

    public ShowStatisticsCommand(UniversityService universityService) {
        this.universityService = universityService;
    }

    @Override
    public void execute(String input) {
        String departmentName = input.split(" ")[1];
        String statistics = universityService.getDepartmentStatistics(departmentName);
        System.out.println(statistics);
    }

    @Override
    public String getKey() {
        return CommandType.SHOW_STATISTICS.name();
    }
}
