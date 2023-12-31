package com.management.oop.test.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.commands.listing.FilterBugByStatus;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
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
    private TaskManagementSystemRepository taskManagementSystemRepository;
    private Command filterBugByStatus;

    @BeforeEach
    public void before() {
        parameters = new ArrayList<>();
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        filterBugByStatus = new FilterBugByStatus(taskManagementSystemRepository);
        taskManagementSystemRepository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);
        taskManagementSystemRepository.createBoard(TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TEAM_NAME);
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
