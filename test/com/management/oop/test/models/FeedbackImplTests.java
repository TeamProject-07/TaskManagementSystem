package com.management.oop.test.models;

import com.management.oop.project.models.contracts.Feedback;
import com.management.oop.project.models.enums.FeedbackStatusEnum;
import com.management.oop.project.models.tasks.FeedbackImpl;
import com.management.oop.test.utils.TaskBaseConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FeedbackImplTests {


    @Test
    public void feedbackImpl_Should_ImplementFeedbackInterface() {
        // Arrange, Act
        FeedbackImpl feedback = initializeTestFeedback();
        // Assert
        Assertions.assertTrue(feedback instanceof Feedback);
    }

    @Test
    public void constructor_Should_ThrowException_When_TitleNameLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new FeedbackImpl(
                        TaskBaseConstants.VALID_ID,
                        TaskBaseConstants.INVALID_TITLE,
                        TaskBaseConstants.VALID_DESCRIPTION,
                        TaskBaseConstants.VALID_RATING,
                        FeedbackStatusEnum.SCHEDULED));
    }

    @Test
    public void constructor_Should_ThrowException_When_DescriptionNameLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new FeedbackImpl(
                        TaskBaseConstants.VALID_ID,
                        TaskBaseConstants.VALID_TITLE,
                        TaskBaseConstants.INVALID_DESCRIPTION,
                        TaskBaseConstants.VALID_RATING,
                        FeedbackStatusEnum.SCHEDULED));
    }

    @Test
    public void constructor_Should_ThrowException_When_RatingValueIsInvalid() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new FeedbackImpl(
                        TaskBaseConstants.VALID_ID,
                        TaskBaseConstants.VALID_TITLE,
                        TaskBaseConstants.VALID_DESCRIPTION,
                        TaskBaseConstants.INVALID_RATING,
                        FeedbackStatusEnum.SCHEDULED));
    }

    @Test
    public void constructor_Should_CreateNewFeedback_When_ParametersAreCorrect() {
        // Arrange
        Feedback feedback = new FeedbackImpl(
                TaskBaseConstants.VALID_ID,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                TaskBaseConstants.VALID_RATING,
                FeedbackStatusEnum.SCHEDULED
        );

        // Act, Assert
        assertAll(
                () -> assertEquals(TaskBaseConstants.VALID_ID, feedback.getId()),
                () -> assertEquals(TaskBaseConstants.VALID_TITLE, feedback.getTitle()),
                () -> assertEquals(TaskBaseConstants.VALID_DESCRIPTION, feedback.getDescription()),
                () -> assertEquals(TaskBaseConstants.VALID_RATING, feedback.getRating()),
                () -> assertSame(FeedbackStatusEnum.SCHEDULED, feedback.getStatus())
        );
    }

    @Test
    public void changeStatus_Should_ChangeTheStatus_OfFeedback() {
        //Arrange
        Feedback feedback = initializeTestFeedback();
        //Act
        feedback.changeStatus(FeedbackStatusEnum.DONE);
        //Assert
        Assertions.assertEquals(feedback.getStatus(), FeedbackStatusEnum.DONE);
    }

    @Test
    public void changeRating_Should_ChangeTheRating_OfFeedback() {
        //Arrange
        Feedback feedback = initializeTestFeedback();
        //Act
        feedback.changeRating(21);
        //Assert
        Assertions.assertEquals(feedback.getRating(), 21);
    }

    public static FeedbackImpl initializeTestFeedback() {
        return new FeedbackImpl(
                TaskBaseConstants.VALID_ID,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                TaskBaseConstants.VALID_RATING,
                FeedbackStatusEnum.SCHEDULED);
    }
}
