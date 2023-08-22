package com.management.oop.test.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.commands.listing.SortFeedback;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.enums.FeedbackStatusEnum;
import com.management.oop.test.utils.TaskBaseConstants;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SortFeedbackTest {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    private List<String> parameters;
    private TaskManagementSystemRepository taskManagementSystemRepository;
    private Command sortFeedback;

    @BeforeEach
    public void before() {
        parameters = new ArrayList<>();
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        sortFeedback = new SortFeedback(taskManagementSystemRepository);
        taskManagementSystemRepository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);
        taskManagementSystemRepository.createBoard(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TEAM_NAME);
        taskManagementSystemRepository.createFeedback(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                TaskBaseConstants.VALID_RATING,
                FeedbackStatusEnum.DONE);
        taskManagementSystemRepository.createFeedback(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                TaskBaseConstants.VALID_RATING,
                FeedbackStatusEnum.DONE);

    }

    @Test
    public void should_ThrowException_When_ArgumentCountInvalid() {
        // Arrange
        parameters = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS + 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> sortFeedback.execute(parameters));
    }
}
