package com.vynnyk.universityconsole.command;

import com.vynnyk.universityconsole.console.UniversityConsole;
import com.vynnyk.universityconsole.service.UniversityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UniversityConsoleTest {

	@Mock
	private UniversityService universityService;

	@InjectMocks
	private UniversityConsole universityConsole;

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		System.setOut(new PrintStream(outContent));
	}

	@Test
	void testGetHeadOfDepartment() {
		String departmentName = "Computer Science";
		String expectedOutput = "Head of Computer Science department is John Doe\n";
		when(universityService.getHeadOfDepartment(departmentName)).thenReturn(expectedOutput);

		// Simulate user input
		String input = "Who is head of department Computer Science\nexit\n";
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		universityConsole.runConsole();

		assertEquals("Welcome to University Console!\nEnter command:\n" + expectedOutput, outContent.toString());
	}

	@Test
	void testGetDepartmentStatistics() {
		String departmentName = "Mathematics";
		String expectedOutput = "assistants - 5, associate professors - 3, professors - 2\n";
		when(universityService.getDepartmentStatistics(departmentName)).thenReturn(expectedOutput);

		// Simulate user input
		String input = "Show Mathematics statistics.\nexit\n"; // Added exit command
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		universityConsole.runConsole();

		assertEquals("Welcome to University Console!\nEnter command:\n" + expectedOutput, outContent.toString());
	}

	@Test
	void testGetAverageSalary() {
		String departmentName = "Physics";
		String expectedOutput = "The average salary of Physics is 70000.0\n";
		when(universityService.getAverageSalary(departmentName)).thenReturn(expectedOutput);

		// Simulate user input
		String input = "Show the average salary for the department Physics.\nexit\n"; // Added exit command
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		universityConsole.runConsole();

		assertEquals("Welcome to University Console!\nEnter command:\n" + expectedOutput, outContent.toString());
	}

	@Test
	void testGetEmployeeCount() {
		String departmentName = "Chemistry";
		String expectedOutput = "Employee count: 10\n";
		when(universityService.getEmployeeCount(departmentName)).thenReturn(expectedOutput);

		// Simulate user input
		String input = "Show count of employee for Chemistry.\nexit\n"; // Added exit command
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		universityConsole.runConsole();

		assertEquals("Welcome to University Console!\nEnter command:\n" + expectedOutput, outContent.toString());
	}

	@Test
	void testGlobalSearch() {
		String template = "van";
		String expectedOutput = "Ivan Petrenko, Petro Ivanov\n";
		when(universityService.globalSearch(template)).thenReturn(expectedOutput);

		// Simulate user input
		String input = "Global search by van\nexit\n"; // Added exit command
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		universityConsole.runConsole();

		assertEquals("Welcome to University Console!\nEnter command:\n" + expectedOutput, outContent.toString());
	}

	@Test
	void testUnknownCommand() {
		String input = "Some unknown command\nexit\n"; // Added exit command
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		universityConsole.runConsole();

		assertEquals("Welcome to University Console!\nEnter command:\nUnknown command.\n", outContent.toString());
	}

	@BeforeEach
	void tearDown() {
		System.setOut(originalOut);
	}
}