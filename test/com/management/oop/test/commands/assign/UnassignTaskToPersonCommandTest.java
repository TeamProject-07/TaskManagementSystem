package com.management.oop.test.commands.assign;

import com.management.oop.project.commands.assign.UnassignTaskToPersonCommand;
import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.PersonImpl;
import com.management.oop.project.models.TeamImpl;
import com.management.oop.project.models.contracts.Board;
import com.management.oop.project.models.contracts.Bug;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.contracts.Team;
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

public class UnassignTaskToPersonCommandTest {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private TaskManagementSystemRepository taskManagementSystemRepository;
    private Command unassignCommand;
    private Team team;
    private Board board;
    private Person person;
    private Bug bug;
    @BeforeEach
    public void before(){
        taskManagementSystemRepository=new TaskManagementSystemRepositoryImpl();
        unassignCommand= new UnassignTaskToPersonCommand(taskManagementSystemRepository);

        this.person=taskManagementSystemRepository.createPerson(TaskBaseConstants.VALID_PERSON_NAME);
        this.team=taskManagementSystemRepository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);
        this.board=taskManagementSystemRepository.createBoard(TaskBaseConstants.VALID_BOARD_NAME, team.getName());
        this.bug=taskManagementSystemRepository.createBug(board.getName(),
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                TaskBaseConstants.STEPS,
                PriorityEnum.HIGH,
                BugSeverityEnum.CRITICAL);
    }
    @Test
    public void should_ThrowException_When_ArgumentCountInvalid() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS-1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> unassignCommand.execute(params));
    }

    @Test
    public void should_UnAssignTask_FromPerson_WhenArguments_AreValid(){
        //Arrange
        List<String> params = List.of(
                person.getName(),
                "1");
        //Act
        bug.assignTask(person);
        String result=unassignCommand.execute(params);
        //Assert
        Assertions.assertEquals("Task with ID 1 was unassigned from person with name personName.", result);
    }
}
