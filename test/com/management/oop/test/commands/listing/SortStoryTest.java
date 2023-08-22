package com.management.oop.test.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.commands.listing.SortStory;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
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

    @BeforeEach
    public void before() {
        parameters = new ArrayList<>();
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        sortStory = new SortStory(taskManagementSystemRepository);
        taskManagementSystemRepository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);
        taskManagementSystemRepository.createBoard(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TEAM_NAME);
        taskManagementSystemRepository.createStory(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                PriorityEnum.MEDIUM,
                StorySizeEnum.LARGE,
                StoryStatusEnum.IN_PROGRESS);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountInvalid() {
        // Arrange
        parameters = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS + 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> sortStory.execute(parameters));
    }
}

