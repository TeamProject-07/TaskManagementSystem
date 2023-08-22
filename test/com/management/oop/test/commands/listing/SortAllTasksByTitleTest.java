package com.management.oop.test.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.commands.listing.SortAllTasksByTitle;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Board;
import com.management.oop.project.models.contracts.Bug;
import com.management.oop.project.models.enums.BugSeverityEnum;
import com.management.oop.project.models.enums.PriorityEnum;
import com.management.oop.test.utils.TaskBaseConstants;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SortAllTasksByTitleTest {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    private TaskManagementSystemRepository taskManagementSystemRepository;

    private Command sortAllTasksByTitle;
    private List<String> parameters;
    private Board board;
    private Bug bug;
    private Bug bug1;

    @BeforeEach
    public void before() {
        parameters = new ArrayList<>();
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        sortAllTasksByTitle = new SortAllTasksByTitle(taskManagementSystemRepository);
        taskManagementSystemRepository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);
        this.board = taskManagementSystemRepository.createBoard(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TEAM_NAME);
        this.bug = taskManagementSystemRepository.createBug(board.getName(),
                TaskBaseConstants.VALID_TITLE_1,
                TaskBaseConstants.VALID_DESCRIPTION,
                TaskBaseConstants.STEPS,
                PriorityEnum.HIGH,
                BugSeverityEnum.CRITICAL);
        this.bug1 = taskManagementSystemRepository.createBug(board.getName(),
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                TaskBaseConstants.STEPS,
                PriorityEnum.HIGH,
                BugSeverityEnum.CRITICAL);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountInvalid() {
        //Arrange
        parameters = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS + 1);

        //Act,Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> sortAllTasksByTitle.execute(parameters));
    }

    @Test
    public void should_SortTasks_When_ExecuteCommand() {
        //Arrange
        List<String> params = new ArrayList<>();
        // Act
        List<String> tasks = new ArrayList<>();
        tasks.add(bug1.getTitle());
        tasks.add(bug.getTitle());
        String expectedResult = tasks.toString();
        String result = sortAllTasksByTitle.execute(params);
        //Assert
        Assertions.assertEquals(expectedResult, result);
    }
}