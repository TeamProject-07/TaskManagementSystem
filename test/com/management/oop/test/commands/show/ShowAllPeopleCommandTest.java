package com.management.oop.test.commands.show;

import com.management.oop.project.commands.show.ShowAllPeopleCommand;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowAllPeopleCommandTest {
    private List<String> parameters;
    private TaskManagementSystemRepositoryImpl repository;
    private ShowAllPeopleCommand showAllPeopleCommand;
    @BeforeEach
    public void before() {
        parameters = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
        showAllPeopleCommand = new ShowAllPeopleCommand(repository);
    }

@Test
    public void execute_Should_ReturnRegisteredPeople_When_PeopleExist(){
    // Arrange
    List<String> parameters = new ArrayList<>();

    // Act
    String result = showAllPeopleCommand.execute(parameters);

    // Assert
    Assertions.assertFalse(result.contains("Alice"));
    Assertions.assertFalse(result.contains("Bob"));
}
    @Test
    public void execute_Should_ReturnNoRegisteredPeople_When_PeopleDoNotExist() {
        // Arrange
        List<String> parameters = new ArrayList<>();

        // Act
        String result = showAllPeopleCommand.execute(parameters);

        // Assert
        Assertions.assertTrue(result.contains("There are no registered people."));
    }
}
