package com.management.oop.test.commands.show;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.commands.show.ShowAllTeamsCommand;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ShowAllTeamsCommandTest {

    private TaskManagementSystemRepository taskManagementSystemRepository;
    private Command showAllTeamsCommand;

    @BeforeEach
    public void before() {
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        showAllTeamsCommand = new ShowAllTeamsCommand(taskManagementSystemRepository);
    }

    @Test
    public void should_NotThrowException_WhenUseCommand() {
        //Arrange
        List<String> params = List.of();
        //Act, Assert
        Assertions.assertDoesNotThrow(() -> showAllTeamsCommand.execute(params));
    }
}
