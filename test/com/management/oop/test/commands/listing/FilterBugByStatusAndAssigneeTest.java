package com.management.oop.test.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.commands.listing.FilterBugByStatusAndAssignee;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
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

public class FilterBugByStatusAndAssigneeTest {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private List<String> parameters;

    private TaskManagementSystemRepository taskManagementSystemRepository;
    private Command filterBugByStatusAndAssignee;
    private Bug bug;

    @BeforeEach
    public void before() {
        parameters = new ArrayList<>();
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        filterBugByStatusAndAssignee = new FilterBugByStatusAndAssignee(taskManagementSystemRepository);
        taskManagementSystemRepository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);
        taskManagementSystemRepository.createBoard(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TEAM_NAME);
        bug = taskManagementSystemRepository.createBug(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                TaskBaseConstants.STEPS,
                PriorityEnum.HIGH,
                BugSeverityEnum.CRITICAL);

    }

    @Test
    public void should_ThrowException_When_ArgumentCountInvalid() {
        // Arrange
        parameters = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> filterBugByStatusAndAssignee.execute(parameters));
    }
}
