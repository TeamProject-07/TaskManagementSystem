package com.management.oop.test.commands.show;

import com.management.oop.project.commands.show.ShowAllTeamBoardsCommand;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.BoardImpl;
import com.management.oop.project.models.TeamImpl;
import com.management.oop.project.models.contracts.Board;
import com.management.oop.project.models.contracts.Team;
import com.management.oop.test.utils.TaskBaseConstants;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShowAllTeamBoardsCommandTest {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private TaskManagementSystemRepository taskManagementSystemRepository;
    private ShowAllTeamBoardsCommand showAllTeamBoardsCommand;


    @BeforeEach
    public void before() {
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        showAllTeamBoardsCommand = new ShowAllTeamBoardsCommand(taskManagementSystemRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        assertThrows(IllegalArgumentException.class,
                () -> showAllTeamBoardsCommand.execute(params));
    }
    @Test
    public void should_ThrowException_When_teamDoesNotExist() {
        List<String> params = List.of(
                TaskBaseConstants.INVALID_TEAM_NAME);
        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> showAllTeamBoardsCommand.execute(params));
    }


    @Test
    public void should_ShowTeamBoards_When_ArgumentsAreValid() {
        // Arrange
        Team team = new TeamImpl(TaskBaseConstants.VALID_TEAM_NAME);
        Board board = new BoardImpl(TaskBaseConstants.VALID_BOARD_NAME);
        team.addBoard(board);

        // Act, Assert
        Assertions.assertEquals(1, team.getBoards().size());
    }


}
