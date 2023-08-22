package com.management.oop.test.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.commands.listing.SortFeedback;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Feedback;
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
    private Feedback feedback;
    private Feedback feedback1;

    @BeforeEach
    public void before() {
        parameters = new ArrayList<>();
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        sortFeedback = new SortFeedback(taskManagementSystemRepository);
        taskManagementSystemRepository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);
        taskManagementSystemRepository.createBoard(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TEAM_NAME);
        this.feedback=taskManagementSystemRepository.createFeedback(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                15,
                FeedbackStatusEnum.DONE);
        this.feedback1=taskManagementSystemRepository.createFeedback(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                20,
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
    @Test
    public void should_SortFeedbacks_WhenExecuteCommand(){
        //Arrange
        List<String>params=new ArrayList<>();
        List<String>resultList=new ArrayList<>();
        resultList.add(feedback.getTitle() + " " + feedback.getRating());
        resultList.add(feedback1.getTitle() + " " + feedback1.getRating());
        String expectedResult= resultList.toString();
        //Act
        String result= sortFeedback.execute(params);
        //Assert
        Assertions.assertEquals(expectedResult, result);
    }
}
