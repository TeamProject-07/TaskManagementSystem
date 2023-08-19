package com.management.oop.test.commands.show;

import com.management.oop.project.commands.show.ShowTeamsActivityCommand;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.EventLogImpl;
import com.management.oop.project.models.TeamImpl;
import com.management.oop.project.models.contracts.Team;
import com.management.oop.test.models.TeamImplTests;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowTeamsActivityCommandTest {
    private List<String> parameters;
    private TaskManagementSystemRepository repository;
    private ShowTeamsActivityCommand showTeamsActivityCommand;

    @BeforeEach
    public void before() {
        parameters = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
        showTeamsActivityCommand = new ShowTeamsActivityCommand(repository);
    }

    @Test
    public void execute_Should_ReturnOutput_When_ValidParameters() {
        Team team = new TeamImpl(TeamImplTests.VALID_TEAM_NAME);
        repository.createTeam(TeamImplTests.VALID_TEAM_NAME);

        parameters.add(TeamImplTests.VALID_TEAM_NAME);

        String expectedOutput = String.format("Show %s activity:", TeamImplTests.VALID_TEAM_NAME);
        String actualOutput = showTeamsActivityCommand.execute(parameters);

        Assertions.assertTrue(actualOutput.contains(expectedOutput));
    }

    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        parameters = TestUtilities.getList(0);

        Assertions.assertThrows(IllegalArgumentException.class, () -> showTeamsActivityCommand.execute(parameters));
    }

    @Test
    public void execute_Should_ThrowException_When_TeamNotExist() {
        parameters.add("aaa");

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
        Team team = new TeamImpl(TeamImplTests.VALID_TEAM_NAME);
        repository.createTeam(TeamImplTests.VALID_TEAM_NAME);

        team.getHistory().add(new EventLogImpl("Some activity"));
        List<String> params = List.of(TeamImplTests.VALID_TEAM_NAME);


        // Act, Assert
        Assertions.assertDoesNotThrow(() -> showTeamsActivityCommand.execute(params));

    }


}
