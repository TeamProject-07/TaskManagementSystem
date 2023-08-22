package com.management.oop.test.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.commands.listing.FilterBugByStatus;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
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

public class FilterBugByStatusTest {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private List<String> parameters;
    private List<String> steps;
    private TaskManagementSystemRepository taskManagementSystemRepository;
    private Command filterBugByStatus;
    private Bug bug;

    @BeforeEach
    public void before() {
        parameters = new ArrayList<>();
        steps = new ArrayList<>();
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        filterBugByStatus = new FilterBugByStatus(taskManagementSystemRepository);
        taskManagementSystemRepository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);
        taskManagementSystemRepository.createBoard(TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TEAM_NAME);
        bug = taskManagementSystemRepository.createBug(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                TaskBaseConstants.STEPS,
                PriorityEnum.HIGH,
                BugSeverityEnum.MAJOR);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountInvalid() {
        // Arrange
        parameters = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> filterBugByStatus.execute(parameters));
    }

    @Test
    public void should_ReturnBug_WhenParameters_AreValid() {
        //Arrange
        parameters.add("Active");
        //Act
        String result = filterBugByStatus.execute(parameters);
        //Assert
        Assertions.assertEquals(ListingHelpers.getAsString(taskManagementSystemRepository.getAllBugs()), result);
    }
}
