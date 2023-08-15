package com.management.oop.test.commands.change;

import com.management.oop.project.commands.change.ChangeFeedbackCommand;
import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.commands.create.CreatePersonCommand;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Feedback;
import com.management.oop.project.models.enums.FeedbackStatusEnum;
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

    private Command command;
    private TaskManagementSystemRepository repository;

    private ChangeFeedbackCommand changeFeedbackCommand;

    @BeforeEach
    public void before() {
        this.repository = new TaskManagementSystemRepositoryImpl();
        this.command = new CreatePersonCommand(repository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

 //  @Test
 //  public void should_ThrowException_When_IsNotNumber() {
 //  Feedback feedback = new FeedbackImpl(
 //          1,
 //          "feedbackName",
 //          "feedbackDescription",
 //          10,
 //          FeedbackStatusEnum.SCHEDULED);
 //  List<String> params = List.of(
 //          "1", "status", "DONE");
 //
 //  changeFeedbackCommand.execute(params);

 //  Assertions.assertEquals(FeedbackStatusEnum.DONE,feedback.getStatus());
 //  }


 //      private String changeStatus(int id, String newTypeOfStatus){
 //          FeedbackStatusEnum feedbackStatusEnum = ParsingHelpers.tryParseEnum(newTypeOfStatus, FeedbackStatusEnum.class);
 //          taskManagementSystemRepository.findFeedbackById(id).changeStatus(feedbackStatusEnum);
 //          return String.format("Status was changed to %s", newTypeOfStatus);
 //      }
 //  }




}
