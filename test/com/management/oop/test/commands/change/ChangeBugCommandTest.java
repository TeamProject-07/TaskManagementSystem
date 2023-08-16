package com.management.oop.test.commands.change;
import com.management.oop.project.commands.change.ChangeBugCommand;
import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.enums.*;
import com.management.oop.test.utils.TaskBaseConstants;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class ChangeBugCommandTest {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    private Command changeBugCommand;
    private TaskManagementSystemRepository repository;

    @BeforeEach
    public void before(){

        repository= new TaskManagementSystemRepositoryImpl();
        changeBugCommand=new ChangeBugCommand(repository);
        repository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);
        repository.createBoard(TaskBaseConstants.VALID_BOARD_NAME, TaskBaseConstants.VALID_TEAM_NAME);
        repository.createBug(TaskBaseConstants.VALID_BOARD_NAME, TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,null, PriorityEnum.MEDIUM, BugSeverityEnum.CRITICAL);
    }
    @Test
    public void should_ThrowException_When_ArgumentCountInvalid() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS-1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeBugCommand.execute(params));
    }
    @Test
    public void should_ThrowException_When_TaskId_IsInvalid() {
        // Arrange
        List<String> params = List.of(
                "invalid Id",
                "priority",
                "HIGH");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeBugCommand.execute(params));
    }
    @Test
    public void should_ChangePriority_WhenArguments_AreValid(){
        // Arrange
        List<String> params = List.of(
                "1",
                "priority",
                "HIGH");
        //Act
        changeBugCommand.execute(params);
        //Assert
        Assertions.assertEquals(repository.findBugById(1).getBugPriorityEnum(),
                PriorityEnum.HIGH);
    }
    @Test
    public void should_ChangeStatus_WhenArguments_AreValid(){
        // Arrange
        List<String> params = List.of(
                "1",
                "status",
                "FIXED");
        //Act
        changeBugCommand.execute(params);
        //Assert
        Assertions.assertEquals(repository.findBugById(1).getStatus(),
                BugStatusEnum.FIXED);
    }
    @Test
    public void should_ChangeSeverity_WhenArguments_AreValid(){
        // Arrange
        List<String> params = List.of(
                "1",
                "severity",
                "MAJOR");
        //Act
        changeBugCommand.execute(params);
        //Assert
        Assertions.assertEquals(repository.findBugById(1).getBugSeverityEnum(),
                BugSeverityEnum.MAJOR);
    }
}
