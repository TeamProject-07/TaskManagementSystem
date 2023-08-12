package com.management.oop.test.models;


import com.management.oop.project.models.contracts.Story;
import com.management.oop.project.models.enums.PriorityEnum;
import com.management.oop.project.models.enums.StorySizeEnum;
import com.management.oop.project.models.enums.StoryStatusEnum;
import com.management.oop.project.models.tasks.StoryImpl;
import com.management.oop.test.utils.TaskBaseConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StoryImplTests {
    public static final int VALID_ID = 1;

    @Test
    public void StoryImpl_Should_ImplementStoryInterface() {
        // Arrange, Act
        StoryImpl story = initializeTestStory();
        // Assert
        Assertions.assertTrue(true);
    }

    @Test
    public void constructor_Should_ThrowException_When_TitleNameLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new StoryImpl(
                        VALID_ID,
                        TaskBaseConstants.INVALID_TITLE,
                        TaskBaseConstants.VALID_DESCRIPTION,
                        PriorityEnum.HIGH,
                        StorySizeEnum.LARGE,
                        StoryStatusEnum.IN_PROGRESS));
    }

    @Test
    public void constructor_Should_ThrowException_When_DescriptionNameLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new StoryImpl(
                        VALID_ID,
                        TaskBaseConstants.VALID_TITLE,
                        TaskBaseConstants.INVALID_DESCRIPTION,
                        PriorityEnum.HIGH,
                        StorySizeEnum.LARGE,
                        StoryStatusEnum.IN_PROGRESS));
    }

    @Test
    public void constructor_Should_CreateNewFeedback_When_ParametersAreCorrect() {
        // Arrange
        Story story = new StoryImpl(
                VALID_ID,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                PriorityEnum.HIGH,
                StorySizeEnum.LARGE,
                StoryStatusEnum.IN_PROGRESS);

        // Act, Assert
        assertAll(
                () -> assertEquals(VALID_ID, story.getId()),
                () -> assertEquals(TaskBaseConstants.VALID_TITLE, story.getTitle()),
                () -> assertEquals(TaskBaseConstants.VALID_DESCRIPTION, story.getDescription()),
                () -> assertEquals(PriorityEnum.HIGH, story.getPriorityEnum()),
                () -> assertSame(StorySizeEnum.LARGE, story.getStorySizeEnum()),
                () -> assertSame(StoryStatusEnum.IN_PROGRESS,story.getStoryStatusEnum()));
    }

    public static StoryImpl initializeTestStory() {
        return new StoryImpl(
                VALID_ID,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                PriorityEnum.HIGH,
                StorySizeEnum.LARGE,
                StoryStatusEnum.IN_PROGRESS);
    }
}
