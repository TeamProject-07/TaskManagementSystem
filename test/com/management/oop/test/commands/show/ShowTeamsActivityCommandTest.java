package com.management.oop.test.commands.show;

import com.management.oop.project.commands.show.ShowTeamsActivityCommand;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.EventLogImpl;
import com.management.oop.project.models.TeamImpl;
import com.management.oop.project.models.contracts.Team;
import com.management.oop.test.utils.TaskBaseConstants;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowTeamsActivityCommandTest {
    private List<String> parameters;
    private TaskManagementSystemRepository taskManagementSystemRepository;
    private ShowTeamsActivityCommand showTeamsActivityCommand;

    @BeforeEach
    public void before() {
        parameters = new ArrayList<>();
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        showTeamsActivityCommand = new ShowTeamsActivityCommand(taskManagementSystemRepository);
    }

    @Test
    public void execute_Should_ReturnOutput_When_ValidParameters() {
        // Arrange
        Team team = new TeamImpl(TaskBaseConstants.VALID_TEAM_NAME);
        taskManagementSystemRepository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);

        // Act
        parameters.add(TaskBaseConstants.VALID_TEAM_NAME);

        String expectedOutput = String.format("Show %s activity:",
                TaskBaseConstants.VALID_TEAM_NAME);
        String actualOutput = showTeamsActivityCommand.execute(parameters);
        // Assert
        Assertions.assertTrue(actualOutput.contains(expectedOutput));
    }

    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        // Arrange
        parameters = TestUtilities.getList(0);

        // Act and Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> showTeamsActivityCommand.execute(parameters));
    }

    @Test
    public void execute_Should_ThrowException_When_TeamNotExist() {
        // Arrange
        parameters.add("aaa");

        // Act and Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> showTeamsActivityCommand.execute(parameters));
    }

    @Test
    public void should_ThrowException_When_TeamHasNoHistory() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> showTeamsActivityCommand.execute(List.of()));
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(ShowTeamsActivityCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> showTeamsActivityCommand.execute(params));
    }

    @Test
    public void should_ShowHistory_When_ArgumentsAreValid() {

        // Arrange
        Team team = new TeamImpl(TaskBaseConstants.VALID_TEAM_NAME);
        taskManagementSystemRepository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);

        team.getHistory().add(new EventLogImpl("Some activity"));
        List<String> params = List.of(TaskBaseConstants.VALID_TEAM_NAME);


        // Act, Assert
        Assertions.assertDoesNotThrow(() -> showTeamsActivityCommand.execute(params));

    }


}
