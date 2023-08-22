package com.management.oop.test.commands.listing;
import com.management.oop.project.commands.listing.FilterAllTasksByTitle;
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

public class FilterAllTasksByTitleTest {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS =  1;

    private List<String> parameters;
    private TaskManagementSystemRepository taskManagementSystemRepository;
    private FilterAllTasksByTitle filterAllTasks;

    @BeforeEach
    public void before() {
        parameters = new ArrayList<>();
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        filterAllTasks = new FilterAllTasksByTitle(taskManagementSystemRepository);
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
                TaskBaseConstants.VALID_TITLE_1,
                TaskBaseConstants.VALID_DESCRIPTION,
                TaskBaseConstants.VALID_RATING,
                FeedbackStatusEnum.DONE);
    }
    @Test
    public void should_ThrowException_When_ArgumentCountInvalid() {
        // Arrange
        parameters = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> filterAllTasks.execute(parameters));
    }
    @Test
    public void should_ReturnTasks_WhenArguments_AreValid(){
        //Arrange
        List<String>parameters=new ArrayList<>();
        parameters.add(TaskBaseConstants.VALID_TITLE);
        //Act
        String result=filterAllTasks.execute(parameters);
        //Assert
        Assertions.assertTrue(result.contains(TaskBaseConstants.VALID_TITLE));
    }
}
