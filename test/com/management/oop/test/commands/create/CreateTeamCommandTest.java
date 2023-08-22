package com.management.oop.test.commands.create;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.commands.create.CreateTeamCommand;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.TeamImpl;
import com.management.oop.project.models.contracts.Team;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CreateTeamCommandTest {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private TaskManagementSystemRepository taskManagementSystemRepository;
    private Command createTeamCommand;
    @BeforeEach
    public void before() {
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        createTeamCommand = new CreateTeamCommand(taskManagementSystemRepository);
    }
    @Test
    public void should_ThrowException_When_ArgumentCountInvalid() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createTeamCommand.execute(params));
    }
    @Test
    public void should_ThrowException_WhenTeam_AlreadyExist() {
        //Arrange
        List<String> params = List.of(
                "validTeamName");
        //Act
        createTeamCommand.execute(params);
        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, ()->createTeamCommand.execute(params));
    }
    @Test
    public void should_CreateTeam_WhenArguments_AreValid() {
        //Arrange
        List<String> params = List.of(
              "validTeamName");
        //Act
        createTeamCommand.execute(params);
        //Assert
        Assertions.assertEquals(1, taskManagementSystemRepository.getTeams().size());
    }
    @Test
    public void createTeam_Should_ReturnString_WhenParameters_AreValid(){
        //Arrange
        List<String> params = List.of(
                "validTeamName");
        //Act
        String result=createTeamCommand.execute(params);
        //Assert
        Assertions.assertEquals("Team with name validTeamName was created.", result);
    }

}
