package com.management.oop.test.commands.add;

import com.management.oop.project.commands.add.AddCommentToATask;
import com.management.oop.project.commands.assign.UnassignTaskToPersonCommand;
import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.BoardImpl;
import com.management.oop.project.models.CommentImpl;
import com.management.oop.project.models.PersonImpl;
import com.management.oop.project.models.TeamImpl;
import com.management.oop.project.models.contracts.*;
import com.management.oop.project.models.enums.BugSeverityEnum;
import com.management.oop.project.models.enums.PriorityEnum;
import com.management.oop.test.commands.assign.UnassignTaskToPersonCommandTest;
import com.management.oop.test.models.BoardImplTests;
import com.management.oop.test.models.BugImplTests;
import com.management.oop.test.utils.TaskBaseConstants;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AddCommentToATaskTest {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private TaskManagementSystemRepository taskManagementSystemRepository;
    private Command addCommentToTaskCommand;
    private Team team;
    private Board board;
    private Person person;
    private Bug bug;
    @BeforeEach
    public void before(){
        taskManagementSystemRepository=new TaskManagementSystemRepositoryImpl();
        addCommentToTaskCommand= new AddCommentToATask(taskManagementSystemRepository);
        this.person = new PersonImpl("person");
        taskManagementSystemRepository.createPerson(person.getName());
        team=new TeamImpl("teamName");
        this.bug= BugImplTests.initializeTestBug();
        List<String>steps=new ArrayList<>();
        board=BoardImplTests.initializeTestBoard();
        taskManagementSystemRepository.createTeam(team.getName());
        taskManagementSystemRepository.createBoard(board.getName(), team.getName());
        taskManagementSystemRepository.createBug(board.getName(), bug.getTitle(),
                TaskBaseConstants.VALID_DESCRIPTION,
                steps,
                PriorityEnum.HIGH,
                BugSeverityEnum.CRITICAL);
    }
    @Test
    public void should_ThrowException_When_ArgumentCountInvalid() {
        // Arrange
        List<String> params =TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS-1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> addCommentToTaskCommand.execute(params));
    }
    @Test
    public void should_ThrowException_When_TaskId_IsInvalid() {
        // Arrange
        List<String> params = List.of(
                "message",
                "person",
                "INVALID_INDEX");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> addCommentToTaskCommand.execute(params));
    }
    @Test
    public void should_ThrownException_WhenTask_DoesNotExist(){
        //Arrange
        List<String> params = List.of(
                "message",
                "person",
                "3");
        //Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, ()->addCommentToTaskCommand.execute(params));
    }
    @Test
    public void should_AddComment_When_ParametersAreValid(){
        //Arrange
        List<String> params = List.of(
                "message",
                "person",
                "1");
        //Act
        addCommentToTaskCommand.execute(params);

        //Assert
        Comment comment=taskManagementSystemRepository.findTaskById(bug.getId()).getComments().get(0);
        Assertions.assertEquals("message", comment.getMessage());
        Assertions.assertEquals("person", comment.getAuthor());
    }
}
