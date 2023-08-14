package com.management.oop.test.commands.show;

import com.management.oop.project.commands.show.ShowTeamsActivityCommand;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
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
    public void setupTest() {
        parameters = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
        showTeamsActivityCommand = new ShowTeamsActivityCommand(repository);
    }

    //TODO
    /*
    @Test
    public void execute_Should_ReturnOutput_When_ValidParameters() {
        Team team = new TeamImpl(TeamImplTests.VALID_TEAM_NAME);
        repository.createTeam(TeamImplTests.VALID_TEAM_NAME);

        parameters.add(TeamImplTests.VALID_TEAM_NAME);

        Assertions.assertEquals(team.getName(), showTeamsActivityCommand.execute(parameters));
    }
*/
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
}
