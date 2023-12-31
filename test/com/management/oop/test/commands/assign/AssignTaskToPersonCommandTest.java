package com.management.oop.test.commands.assign;
import com.management.oop.project.commands.assign.AssignTaskToPersonCommand;
import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.PersonImpl;
import com.management.oop.project.models.TeamImpl;
import com.management.oop.project.models.contracts.*;
import com.management.oop.project.models.enums.BugSeverityEnum;
import com.management.oop.project.models.enums.PriorityEnum;
import com.management.oop.test.models.BoardImplTests;
import com.management.oop.test.models.BugImplTests;
import com.management.oop.test.utils.TaskBaseConstants;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AssignTaskToPersonCommandTest {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private TaskManagementSystemRepository repository;

    private Command assignCommand;
    private Team team;
    private Board board;
    private Person person;
    private Assignable task;
    @BeforeEach
    public void before(){
        repository = new TaskManagementSystemRepositoryImpl();
        assignCommand = new AssignTaskToPersonCommand(repository);
        List<String> steps = new ArrayList<>();
        this.person=repository.createPerson("personName");
        this.team=repository.createTeam("teamName");
        this.board=repository.createBoard("boardName", team.getName());
        this.task=repository.createBug(board.getName(), "validTitle",
                TaskBaseConstants.VALID_DESCRIPTION,
                steps,
                PriorityEnum.HIGH,
                BugSeverityEnum.CRITICAL);
    }
    @Test
    public void should_ThrowException_When_ArgumentCountInvalid() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS-1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> assignCommand.execute(params));
    }
    @Test
    public void should_ThrowException_When_TaskId_IsInvalid() {
        // Arrange
        List<String> params = List.of(
                "message",
                "person",
                "INVALID_INDEX");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> assignCommand.execute(params));
    }
    @Test
    public void should_ThrownException_WhenTask_DoesNotExist(){
        //Arrange
        List<String> params = List.of(
                "message",
                "person",
                "1");
        //Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, ()-> assignCommand.execute(params));
    }
  @Test
  public void should_AssignTask_ToPerson(){
      //Arrange
       List<String> params = List.of(
               person.getName(), "1");
      //Act
      assignCommand.execute(params);
      //Assert
      Assertions.assertEquals(person.getName(), task.getAssignee().getName());
  }
}
