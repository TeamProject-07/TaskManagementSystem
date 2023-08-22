package com.management.oop.test.commands.add;

import com.management.oop.project.commands.add.AddPersonToTeamCommand;
import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.contracts.Team;
import com.management.oop.test.models.PersonImplTests;
import com.management.oop.test.utils.TaskBaseConstants;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AddPersonToTeamCommandTest {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private List<String> parameters;
    private TaskManagementSystemRepository repository;
    private Command addPersonToTeam;

    @BeforeEach
    public void before() {
        parameters = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
        addPersonToTeam = new AddPersonToTeamCommand(repository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> addPersonToTeam.execute(params));
    }

    @Test
    public void execute_Should_AddNewPersonToTeam_When_ValidParameters() {
        // Arrange
        repository.createPerson(TaskBaseConstants.VALID_PERSON_NAME);
        repository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);

        // Act
        parameters.add(TaskBaseConstants.VALID_PERSON_NAME);
        parameters.add(TaskBaseConstants.VALID_TEAM_NAME);
        addPersonToTeam.execute(parameters);
        Person person = repository.findPersonByName(TaskBaseConstants.VALID_PERSON_NAME);
        Team team = repository.findTeamByName(TaskBaseConstants.VALID_TEAM_NAME);

        // Assert
        Assertions.assertTrue(team.getPeople().contains(person));
    }

    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        // Arrange
        parameters = TestUtilities.getList(1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> addPersonToTeam.execute(parameters));
    }

    @Test
    public void execute_Should_ThrowException_When_TeamNotExist() {
        // Arrange
        repository.createPerson(TaskBaseConstants.VALID_PERSON_NAME);
        repository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);

        // Act
        parameters.add(TaskBaseConstants.VALID_PERSON_NAME);
        parameters.add(TaskBaseConstants.INVALID_TEAM_NAME);

        // Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> addPersonToTeam.execute(parameters));
    }
    @Test
    public void execute_Should_ThrowException_When_PersonNotExist() {
        // Arrange
        repository.createPerson(TaskBaseConstants.VALID_PERSON_NAME);
        repository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);

        // Act
        parameters.add(TaskBaseConstants.INVALID_PERSON_NAME);
        parameters.add(TaskBaseConstants.VALID_TEAM_NAME);

        // Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> addPersonToTeam.execute(parameters));
    }
}
