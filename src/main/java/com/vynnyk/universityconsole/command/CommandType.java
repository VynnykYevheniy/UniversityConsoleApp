package com.vynnyk.universityconsole.command;

import lombok.Getter;

@Getter
public enum CommandType {
    HEAD_OF_DEPARTMENT("Who is head of department"),
    SHOW_STATISTICS("Show "),
    AVERAGE_SALARY("Show the average salary for the department"),
    COUNT_EMPLOYEES("Show count of employee for"),
    GLOBAL_SEARCH("Global search by");

    private final String commandFormat;

    CommandType(String commandFormat) {
        this.commandFormat = commandFormat;
    }

	public static CommandType fromInput(String input) {
        for (CommandType commandType : values()) {
            if (input.matches(commandType.getRegex())) {
                return commandType;
            }
        }
        return null; // Если ни одна команда не подошла
    }

    private String getRegex() {
		return switch (this) {
			case HEAD_OF_DEPARTMENT, AVERAGE_SALARY, COUNT_EMPLOYEES, GLOBAL_SEARCH -> commandFormat + " .+";
			case SHOW_STATISTICS -> "^Show\\s+.+?\\s+statistics\\.$";
		};
    }
}
