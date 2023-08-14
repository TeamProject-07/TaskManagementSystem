package com.management.oop.test.commands.create;

import com.management.oop.project.commands.create.CreateNewStory;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.enums.PriorityEnum;
import com.management.oop.project.models.enums.StorySizeEnum;
import com.management.oop.project.models.enums.StoryStatusEnum;
import com.management.oop.project.models.tasks.StoryImpl;
import com.management.oop.test.models.StoryImplTests;
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
    @BeforeEach
    public void setupTest() {
        parameters = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
        createNewStory = new CreateNewStory(repository);
    }
    //TODO
    /*
    @Test
    public void execute_Should_AddNewStoryToRepository_When_ValidParameters() {
        parameters.add(TaskBaseConstants.VALID_BOARD_NAME);
        parameters.add(TaskBaseConstants.VALID_TITLE);
        parameters.add(TaskBaseConstants.VALID_DESCRIPTION);
        parameters.add(PriorityEnum.HIGH.toString());
        parameters.add(StorySizeEnum.LARGE.toString());
        parameters.add(StoryStatusEnum.IN_PROGRESS.toString());
        createNewStory.execute(parameters);

        Assertions.assertTrue(repository.boardExist(TaskBaseConstants.VALID_BOARD_NAME));
    }
*/
    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        parameters = TestUtilities.getList(1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> createNewStory.execute(parameters));
    }
    @Test
    public void execute_Should_ThrowException_When_InvalidPriority() {
        parameters.add(TaskBaseConstants.VALID_BOARD_NAME);
        parameters.add(TaskBaseConstants.VALID_TITLE);
        parameters.add(TaskBaseConstants.VALID_DESCRIPTION);
        parameters.add("priority");
        parameters.add(StorySizeEnum.LARGE.toString());
        parameters.add(StoryStatusEnum.IN_PROGRESS.toString());

        Assertions.assertThrows(IllegalArgumentException.class, () -> createNewStory.execute(parameters));
    }
    //TODO
    /*
    @Test
    public void execute_Should_ThrowException_When_DuplicateProductName() {
        repository.createStory(TaskBaseConstants.VALID_BOARD_NAME, TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION, PriorityEnum.HIGH, StorySizeEnum.LARGE, StoryStatusEnum.IN_PROGRESS);

        parameters.add(TaskBaseConstants.VALID_BOARD_NAME);
        parameters.add(TaskBaseConstants.VALID_TITLE);
        parameters.add(TaskBaseConstants.VALID_DESCRIPTION);
        parameters.add(PriorityEnum.HIGH.toString());
        parameters.add(StorySizeEnum.LARGE.toString());
        parameters.add(StoryStatusEnum.IN_PROGRESS.toString());

        Assertions.assertThrows(IllegalArgumentException.class, () -> createNewStory.execute(parameters));
    }

     */
}
