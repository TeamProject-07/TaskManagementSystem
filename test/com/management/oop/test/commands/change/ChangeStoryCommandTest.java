package com.management.oop.test.commands.change;

import com.management.oop.project.commands.change.ChangeStoryCommand;
import com.management.oop.project.commands.contracts.Command;
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

import java.util.List;

public class ChangeStoryCommandTest {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    private TaskManagementSystemRepository taskManagementSystemRepository;
    private Command changeStoryCommand;
    @BeforeEach
    public void before(){
        taskManagementSystemRepository= new TaskManagementSystemRepositoryImpl();
        changeStoryCommand=new ChangeStoryCommand(taskManagementSystemRepository);
        taskManagementSystemRepository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);
        taskManagementSystemRepository.createBoard(TaskBaseConstants.VALID_BOARD_NAME, TaskBaseConstants.VALID_TEAM_NAME);
        taskManagementSystemRepository.createStory(TaskBaseConstants.VALID_BOARD_NAME, "taskStoryTitle",
                TaskBaseConstants.VALID_DESCRIPTION, PriorityEnum.MEDIUM, StorySizeEnum.LARGE, StoryStatusEnum.IN_PROGRESS);
    }
    @Test
    public void should_ThrowException_When_ArgumentCountInvalid() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS-1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeStoryCommand.execute(params));
    }
    @Test
    public void should_ThrowException_When_TaskId_IsInvalid() {
        // Arrange
        List<String> params = List.of(
                "invalid Id",
                "priority",
                "HIGH");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeStoryCommand.execute(params));
    }
    @Test
    public void should_ChangePriority_WhenArguments_AreValid(){
        // Arrange
        List<String> params = List.of(
                "1",
                "priority",
                "HIGH");
        //Act
        changeStoryCommand.execute(params);
        //Assert
        Assertions.assertEquals(taskManagementSystemRepository.findStoryById(1).getPriorityEnum(),
                PriorityEnum.HIGH);
    }
    @Test
    public void should_ChangeSize_WhenArguments_AreValid(){
        // Arrange
        List<String> params = List.of(
                "1",
                "size",
                "Medium");
        //Act
        changeStoryCommand.execute(params);
        //Assert
        Assertions.assertEquals(taskManagementSystemRepository.findStoryById(1).getStorySizeEnum(),
                StorySizeEnum.MEDIUM);
    }
    @Test
    public void should_ChangeStatus_WhenArguments_AreValid(){
        // Arrange
        List<String> params = List.of(
                "1",
                "status",
                "DONE");
        //Act
        changeStoryCommand.execute(params);
        //Assert
        Assertions.assertEquals(taskManagementSystemRepository.findStoryById(1).getStoryStatusEnum(),
                StoryStatusEnum.DONE);
    }
}
