package com.management.oop.test.commands.show;

import com.management.oop.project.commands.show.ShowAllTeamsMembersCommand;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.TeamImpl;
import com.management.oop.project.models.contracts.Team;
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
    private TaskManagementSystemRepository taskManagementSystemRepository;
    private ShowAllTeamsMembersCommand showAllTeamsMember;

    @BeforeEach
    public void before() {
        parameters = new ArrayList<>();
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        showAllTeamsMember = new ShowAllTeamsMembersCommand(taskManagementSystemRepository);
        taskManagementSystemRepository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);

    }

    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        parameters = TestUtilities.getList(0);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> showAllTeamsMember.execute(parameters));
    }

    @Test
    public void execute_Should_ThrowException_When_TeamNotExist() {
        // Arrange
        parameters.add("aaa");
        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> showAllTeamsMember.execute(parameters));
    }

    @Test
    public void should_ThrowException_When_NoTeamMembersInTeam() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> showAllTeamsMember.execute(List.of()));
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> showAllTeamsMember.execute(params));
    }

    @Test
    public void should_ShowTeamsMembersWhen_ArgumentsAreValid() {
        // Arrange
        TeamImpl team = new TeamImpl(TaskBaseConstants.VALID_TEAM_NAME);
        List<String> parameters = List.of(team.getName());
        // Act, Assert
        Assertions.assertAll(() -> Assertions.assertDoesNotThrow(() -> showAllTeamsMember.execute(parameters)));
    }

    @Test
    public void should_ThrowException_WhenTeamIsEmpty() {
        // Arrange
        Team team = taskManagementSystemRepository.createTeam("EmptyTeam");
        // Act
        String result = showAllTeamsMember.execute(List.of("EmptyTeam"));
        // Assert
        Assertions.assertEquals("Team with name EmptyTeam is empty.", result);
    }

    @Test
    public void should_ThrowException_WhenTeamIsNonEmpty() {
        // Arrange
        Team team = taskManagementSystemRepository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);
        taskManagementSystemRepository.findTeamByName(team.getName());
        // Act
        List<String> parameters = List.of(team.getName());
        // Assert
        Assertions.assertDoesNotThrow(() -> showAllTeamsMember.execute(List.of(team.getName())));
    }


}
