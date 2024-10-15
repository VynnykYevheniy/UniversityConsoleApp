package com.vynnyk.universityconsole.console;

import com.vynnyk.universityconsole.command.Command;
import com.vynnyk.universityconsole.command.CommandType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Scanner;

@Component
public class UniversityConsole {
	private static final String WELCOME_COMMAND = "Welcome to University Console!";
	private static final String ENTER_COMMAND = "Enter command:";
	private static final String UNKNOWN_COMMAND_MESSAGE = "Unknown command.";

	private final Map<String, Command> commandMap;

	public UniversityConsole(@Qualifier("initCommandMap") Map<String, Command> commandMap) {
		this.commandMap = commandMap;
	}

	@EventListener(ApplicationReadyEvent.class)
	public void runConsole() {
		Scanner scanner = new Scanner(System.in);
		System.out.println(WELCOME_COMMAND);
		while (true) {
			System.out.println(ENTER_COMMAND);
			String input = scanner.nextLine();
			CommandType commandType = CommandType.fromInput(input);
			if (commandType != null) {
				String commandKey = commandType.name();
				commandMap.get(commandKey).execute(input);
			} else {
				System.out.println(UNKNOWN_COMMAND_MESSAGE);
			}
		}
	}
}