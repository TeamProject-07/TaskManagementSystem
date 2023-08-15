package com.management.oop.test.commands.show;

import com.management.oop.project.commands.show.ShowAllPeopleCommand;
import com.management.oop.project.commands.show.ShowTeamsActivityCommand;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.models.EventLogImpl;
import com.management.oop.project.models.PersonImpl;
import com.management.oop.project.models.TeamImpl;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.contracts.Team;
import com.management.oop.project.utils.ListingHelpers;
import com.management.oop.test.models.PersonImplTests;
import com.management.oop.test.models.TeamImplTests;
import com.management.oop.test.utils.TestUtilities;
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
    public void setupTest() {
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
