package com.vynnyk.universityconsole.command;

public interface Command {
	void execute(String input);

	String getKey();
}
