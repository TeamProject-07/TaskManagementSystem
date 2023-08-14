package com.management.oop.test.commands.show;

import com.management.oop.project.commands.show.ShowAllTeamsMembersCommand;
import com.management.oop.project.commands.show.ShowTeamsActivityCommand;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.PersonImpl;
import com.management.oop.project.models.TeamImpl;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.contracts.Team;
import com.management.oop.test.models.PersonImplTests;
import com.management.oop.test.models.TeamImplTests;
import com.management.oop.test.utils.TaskBaseConstants;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowAllTeamsMembersCommandTest {
    private List<String> parameters;
    private TaskManagementSystemRepository repository;
    private ShowAllTeamsMembersCommand showAllTeamsMembercommand;

    @BeforeEach
    public void setupTest() {
        parameters = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
        showAllTeamsMembercommand = new ShowAllTeamsMembersCommand(repository);
    }
    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        parameters = TestUtilities.getList(0);

        Assertions.assertThrows(IllegalArgumentException.class, () -> showAllTeamsMembercommand.execute(parameters));
    }
    /*
    @Test
    public void execute_Should_ReturnOutput_When_ValidParameters() {
        Team team = new TeamImpl(TaskBaseConstants.VALID_TEAM_NAME);
        repository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);

        parameters.add(TaskBaseConstants.VALID_TEAM_NAME);

        Assertions.assertEquals(team.getAsString(), showAllTeamsMembercommand.execute(parameters));
    }
     */
    @Test
    public void execute_Should_ThrowException_When_TeamNotExist() {
        parameters.add("aaa");

        Assertions.assertThrows(IllegalArgumentException.class, () -> showAllTeamsMembercommand.execute(parameters));
    }

}
