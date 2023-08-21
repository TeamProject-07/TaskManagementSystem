package com.management.oop.test.commands.create;

import com.management.oop.project.commands.create.CreateNewStory;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Story;
import com.management.oop.project.models.enums.PriorityEnum;
import com.management.oop.project.models.enums.StorySizeEnum;
import com.management.oop.project.models.enums.StoryStatusEnum;
import com.management.oop.project.models.tasks.StoryImpl;
import com.management.oop.test.utils.TaskBaseConstants;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateNewStoryTest {
    private List<String> parameters;
    private TaskManagementSystemRepository repository;
    private CreateNewStory createNewStory;
    private Story story;

    @BeforeEach
    public void before() {
        parameters = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
        createNewStory = new CreateNewStory(repository);
        repository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);
        repository.createBoard(TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TEAM_NAME);
        this.story = new StoryImpl(1,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                PriorityEnum.HIGH,
                StorySizeEnum.MEDIUM,
                StoryStatusEnum.IN_PROGRESS);

    }

    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        // Arrange
        parameters = TestUtilities.getList(1);

        // Act and Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> createNewStory.execute(parameters));
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidPriority() {

        // Arrange
        parameters.add(TaskBaseConstants.VALID_BOARD_NAME);
        parameters.add(TaskBaseConstants.VALID_TITLE);
        parameters.add(TaskBaseConstants.VALID_DESCRIPTION);
        parameters.add("priority");
        parameters.add(StorySizeEnum.LARGE.toString());
        parameters.add(StoryStatusEnum.IN_PROGRESS.toString());

        // Act and Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> createNewStory.execute(parameters));
    }

    @Test
    public void execute_Should_ThrowException_When_DuplicateBoardName() {
        // Arrange
        TaskManagementSystemRepository repository = new TaskManagementSystemRepositoryImpl();
        CreateNewStory createNewStory = new CreateNewStory(repository);
        List<String> parameters = new ArrayList<>();

        // Act
        parameters.add(TaskBaseConstants.INVALID_BOARD_NAME);
        parameters.add(TaskBaseConstants.VALID_TITLE);
        parameters.add(TaskBaseConstants.VALID_DESCRIPTION);
        parameters.add(PriorityEnum.HIGH.toString());
        parameters.add(StorySizeEnum.LARGE.toString());
        parameters.add(StoryStatusEnum.IN_PROGRESS.toString());

        // Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> createNewStory.execute(parameters));
    }

    @Test
    public void should_CreateStory_WhenArguments_AreValid() {
        // Arrange
        List<String> params = List.of(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                PriorityEnum.HIGH.toString(),
                StorySizeEnum.LARGE.toString(),
                StoryStatusEnum.IN_PROGRESS.toString());

        // Act
        String result = createNewStory.execute(params);

        // Assert
        Assertions.assertEquals("Story with ID 1 was created.", result);
        Assertions.assertEquals(repository.getAllStories().size(), 1);

    }


}
