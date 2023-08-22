package com.management.oop.test.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.commands.listing.SortStory;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Board;
import com.management.oop.project.models.contracts.Story;
import com.management.oop.project.models.enums.PriorityEnum;
import com.management.oop.project.models.enums.StorySizeEnum;
import com.management.oop.project.models.enums.StoryStatusEnum;
import com.management.oop.test.utils.TaskBaseConstants;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SortStoryTest {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    private List<String> parameters;
    private TaskManagementSystemRepository taskManagementSystemRepository;
    private Command sortStory;

    private Story story;

    private Story story1;

    private Board board;

    @BeforeEach
    public void before() {
        parameters = new ArrayList<>();
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        sortStory = new SortStory(taskManagementSystemRepository);
        taskManagementSystemRepository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);
        this.board = taskManagementSystemRepository.createBoard(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TEAM_NAME);
        this.story = taskManagementSystemRepository.createStory(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                PriorityEnum.MEDIUM,
                StorySizeEnum.LARGE,
                StoryStatusEnum.IN_PROGRESS);
        this.story1 = taskManagementSystemRepository.createStory(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                PriorityEnum.HIGH,
                StorySizeEnum.SMALL,
                StoryStatusEnum.NOT_DONE);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountInvalid() {
        // Arrange
        parameters = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS + 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> sortStory.execute(parameters));
    }

    @Test
    public void should_SortTasks_When_ExecuteCommand() {
        //Arrange
        List<String> params = new ArrayList<>();
        // Act
        List<String> stories = new ArrayList<>();
        stories.add(story1.getTitle() + " " + story1.getPriorityEnum() + " " + story1.getStorySizeEnum());
        stories.add(story1.getTitle() + " " + story.getPriorityEnum() + " " + story.getStorySizeEnum());
        String result = sortStory.execute(params);

        //Assert
        Assertions.assertEquals(stories.toString(),
                result);
    }
}

