package com.management.oop.test.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.commands.listing.FilterFeedbackByStatus;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Feedback;
import com.management.oop.project.models.enums.FeedbackStatusEnum;
import com.management.oop.project.models.tasks.FeedbackImpl;
import com.management.oop.project.utils.ListingHelpers;
import com.management.oop.test.utils.TaskBaseConstants;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FilterFeedbackByStatusTest {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private List<String> parameters;
    private TaskManagementSystemRepository taskManagementSystemRepository;
    private Command filterFeedbackByStatus;

    private Feedback feedback;

    @BeforeEach
    public void before() {
        parameters = new ArrayList<>();
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        filterFeedbackByStatus = new FilterFeedbackByStatus(taskManagementSystemRepository);
        taskManagementSystemRepository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);
        taskManagementSystemRepository.createBoard(TaskBaseConstants.VALID_BOARD_NAME, TaskBaseConstants.VALID_TEAM_NAME);
        feedback= new FeedbackImpl(1, TaskBaseConstants.VALID_TITLE, TaskBaseConstants.VALID_DESCRIPTION ,
                TaskBaseConstants.VALID_RATING, FeedbackStatusEnum.NEW);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountInvalid() {
        // Arrange
        parameters = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> filterFeedbackByStatus.execute(parameters));
    }

    @Test
    public void should_ReturnFeedback_WhenParameters_AreValid() {
        //Arrange
        parameters.add(FeedbackStatusEnum.NEW.toString());
        //Act
        String result = filterFeedbackByStatus.execute(parameters);
        //Assert
        Assertions.assertEquals(ListingHelpers.getAsString(taskManagementSystemRepository.getAllFeedback()), result);
    }
}
