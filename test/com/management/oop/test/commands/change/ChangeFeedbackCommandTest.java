package com.management.oop.test.commands.change;

import com.management.oop.project.commands.change.ChangeFeedbackCommand;
import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.commands.create.CreatePersonCommand;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Feedback;
import com.management.oop.project.models.enums.FeedbackStatusEnum;
import com.management.oop.project.models.enums.PriorityEnum;
import com.management.oop.project.models.enums.StorySizeEnum;
import com.management.oop.project.models.enums.StoryStatusEnum;
import com.management.oop.project.models.tasks.FeedbackImpl;
import com.management.oop.project.utils.ParsingHelpers;
import com.management.oop.test.models.FeedbackImplTests;
import com.management.oop.test.utils.TaskBaseConstants;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChangeFeedbackCommandTest {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private ChangeFeedbackCommand changeFeedbackCommand;
    private TaskManagementSystemRepository repository;


    @BeforeEach
    public void before() {
        repository = new TaskManagementSystemRepositoryImpl();
        changeFeedbackCommand = new ChangeFeedbackCommand (repository);
        repository.createTeam("teamName");
        repository.createBoard("boardName", "teamName");
        repository.createFeedback("boardName","feedbackName","description",10,FeedbackStatusEnum.NEW);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> changeFeedbackCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_TaskId_IsInvalid() {
        // Arrange
        List<String> params = List.of(
                "invalid Id",
                "priority",
                "HIGH");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeFeedbackCommand.execute(params));
    }
    @Test
    public void should_ChangeStatus_WhenArguments_AreValid(){
        // Arrange
        List<String> params = List.of(
                "1",
                "status",
                "DONE");
        //Act
        changeFeedbackCommand.execute(params);
        //Assert
        Assertions.assertEquals(repository.findFeedbackById(1).getStatus(),
                FeedbackStatusEnum.DONE);
    }
   @Test
   public void should_ChangeRating_WhenArguments_AreValid(){
       // Arrange
       List<String> params = List.of(
               "1",
               "rating",
               "99");
       //Act
       changeFeedbackCommand.execute(params);
       //Assert
       Assertions.assertEquals(repository.findFeedbackById(1).getRating(),
               99);
   }





}
