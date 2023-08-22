package com.management.oop.test.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.commands.listing.SortBug;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Board;
import com.management.oop.project.models.contracts.Bug;
import com.management.oop.project.models.enums.BugSeverityEnum;
import com.management.oop.project.models.enums.PriorityEnum;
import com.management.oop.project.utils.ListingHelpers;
import com.management.oop.test.utils.TaskBaseConstants;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SortBugTest {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    private List<String> parameters;
    private TaskManagementSystemRepository taskManagementSystemRepository;
    private Command sortBug;
    private Board board;
    private Bug bug;
    private Bug bug1;

    @BeforeEach
    public void before() {
        parameters = new ArrayList<>();
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        sortBug = new SortBug(taskManagementSystemRepository);
        taskManagementSystemRepository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);
        this.board=taskManagementSystemRepository.createBoard(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TEAM_NAME);
        this.bug=taskManagementSystemRepository.createBug(board.getName(),
                "validTitle1",
                TaskBaseConstants.VALID_DESCRIPTION,
                TaskBaseConstants.STEPS,
                PriorityEnum.LOW,
                BugSeverityEnum.MAJOR);
        this.bug1=taskManagementSystemRepository.createBug(board.getName(),
                "validTitle",
                TaskBaseConstants.VALID_DESCRIPTION,
                TaskBaseConstants.STEPS,
                PriorityEnum.HIGH,
                BugSeverityEnum.CRITICAL);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountInvalid() {
        // Arrange
        parameters = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS + 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> sortBug.execute(parameters));
    }
    @Test
    public void should_SortBugs_WhenExecuteCommand(){
        //Arrange
        List<String>params=new ArrayList<>();
        List<String>resultList=new ArrayList<>();
        resultList.add(bug1.getTitle() + " "+ bug1.getBugPriorityEnum() + " " +bug1.getBugSeverityEnum());
        resultList.add(bug.getTitle() + " "+ bug.getBugPriorityEnum() + " " +bug.getBugSeverityEnum());
        String expectedResult= resultList.toString();
        //Act
        String result= sortBug.execute(params);
        //Assert
        Assertions.assertEquals(expectedResult, result);
    }
}
