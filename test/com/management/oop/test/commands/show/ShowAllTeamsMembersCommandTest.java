package com.management.oop.test.commands.show;

import com.management.oop.project.commands.show.ShowAllTeamsMembersCommand;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.TeamImpl;
import com.management.oop.project.models.contracts.Team;
import com.management.oop.test.models.TeamImplTests;
import com.management.oop.test.utils.TaskBaseConstants;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowAllTeamsMembersCommandTest {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private List<String> parameters;
    private TaskManagementSystemRepository repository;
    private ShowAllTeamsMembersCommand showAllTeamsMembercommand;

    @BeforeEach
    public void before() {
        parameters = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
        showAllTeamsMembercommand = new ShowAllTeamsMembersCommand(repository);
        repository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);

    }

    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        parameters = TestUtilities.getList(0);

        Assertions.assertThrows(IllegalArgumentException.class, () -> showAllTeamsMembercommand.execute(parameters));
    }

    @Test
    public void execute_Should_ThrowException_When_TeamNotExist() {
        parameters.add("aaa");

        Assertions.assertThrows(IllegalArgumentException.class, () -> showAllTeamsMembercommand.execute(parameters));
    }

    @Test
    public void should_ThrowException_When_NoTeamMembersInTeam() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> showAllTeamsMembercommand.execute(List.of()));
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> showAllTeamsMembercommand.execute(params));
    }

    @Test
    public void should_ShowTeamsMembersWhen_ArgumentsAreValid() {
        // Arrange
        TeamImpl team = new TeamImpl(TeamImplTests.VALID_TEAM_NAME);
        List<String> parameters = List.of(team.getName());
        // Act, Assert
        Assertions.assertAll(() -> Assertions.assertDoesNotThrow(() -> showAllTeamsMembercommand.execute(parameters)));
    }

    @Test
    public void should_ThrowException_WhenTeamIsEmpty() {
        Team team = repository.createTeam("EmptyTeam");
        String result = showAllTeamsMembercommand.execute(List.of("EmptyTeam"));
        Assertions.assertEquals("", result);

    }

    @Test
    public void should_ThrowException_WhenTeamIsNonEmpty() {
        Team team = repository.createTeam("TeamWithMembers");
        repository.findTeamByName(team.getName());
        List<String> parameters = List.of(team.getName());

        Assertions.assertDoesNotThrow(() -> showAllTeamsMembercommand.execute(List.of(team.getName())));
    }


}
