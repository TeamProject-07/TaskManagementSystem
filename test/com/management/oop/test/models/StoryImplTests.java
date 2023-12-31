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
    @Test
    public void constructor_Should_ThrowException_When_TitleNameLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new StoryImpl(
                        TaskBaseConstants.VALID_ID,
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
                        TaskBaseConstants.VALID_ID,
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
                TaskBaseConstants.VALID_ID,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                PriorityEnum.HIGH,
                StorySizeEnum.LARGE,
                StoryStatusEnum.IN_PROGRESS);

        // Act, Assert
        assertAll(
                () -> assertEquals(TaskBaseConstants.VALID_ID, story.getId()),
                () -> assertEquals(TaskBaseConstants.VALID_TITLE, story.getTitle()),
                () -> assertEquals(TaskBaseConstants.VALID_DESCRIPTION, story.getDescription()),
                () -> assertEquals(PriorityEnum.HIGH, story.getPriorityEnum()),
                () -> assertSame(StorySizeEnum.LARGE, story.getStorySizeEnum()),
                () -> assertSame(StoryStatusEnum.IN_PROGRESS, story.getStoryStatusEnum()));
    }

    @Test
    public void ChangePriority_Should_ChangeStoryPriority() {
        StoryImpl story = initializeTestStory();
        story.changePriorityEnum(PriorityEnum.MEDIUM);

        Assertions.assertEquals(story.getPriorityEnum(), PriorityEnum.MEDIUM);
    }

    @Test
    public void ChangeSize_Should_ChangeStorySize() {
        StoryImpl story = initializeTestStory();
        story.changeSize(StorySizeEnum.SMALL);

        Assertions.assertEquals(story.getStorySizeEnum(), StorySizeEnum.SMALL);
    }

    @Test
    public void ChangeStatus_Should_ChangeStoryStatus() {
        StoryImpl story = initializeTestStory();
        story.changeStoryStatusEnum(StoryStatusEnum.DONE);

        Assertions.assertEquals(story.getStoryStatusEnum(), StoryStatusEnum.DONE);
    }


    public static StoryImpl initializeTestStory() {
        return new StoryImpl(
                TaskBaseConstants.VALID_ID,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                PriorityEnum.HIGH,
                StorySizeEnum.LARGE,
                StoryStatusEnum.IN_PROGRESS);
    }
}
