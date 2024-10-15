package com.vynnyk.universityconsole.config;

import com.vynnyk.universityconsole.command.Command;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class Config {

	@Bean
	public Map<String, Command> initCommandMap(List<Command> commands) {
		return commands.stream().collect(Collectors.toMap(Command::getKey, Function.identity()));
	}
}