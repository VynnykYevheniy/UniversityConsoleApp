package com.vynnyk.universityconsole.command;

import com.vynnyk.universityconsole.service.UniversityService;
import org.springframework.stereotype.Component;

@Component
public class GlobalSearchCommand implements Command {
	private final UniversityService universityService;

	public GlobalSearchCommand(UniversityService universityService) {
		this.universityService = universityService;
	}

	@Override
	public void execute(String input) {
		String template = input.substring(CommandType.GLOBAL_SEARCH.getCommandFormat().length()).trim();
		String result = universityService.globalSearch(template);
		System.out.println(result);
	}

	@Override
	public String getKey() {
		return CommandType.GLOBAL_SEARCH.name();
	}
}