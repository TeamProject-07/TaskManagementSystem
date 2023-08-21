package com.management.oop.test.commands.create;

import com.management.oop.project.commands.create.CreateBoardCommand;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.test.utils.TaskBaseConstants;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateBoardCommandTest {
    private List<String> parameters;
    private TaskManagementSystemRepository repository;
    private CreateBoardCommand createBoardCommand;

    @BeforeEach
    public void before() {
        parameters = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
        createBoardCommand = new CreateBoardCommand(repository);
    }

    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        parameters = TestUtilities.getList(0);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> createBoardCommand.execute(parameters));
    }
    @Test
    public void execute_Should_ThrowException_When_InvalidBoardName() {
        parameters.add(TaskBaseConstants.INVALID_BOARD_NAME);
        parameters.add(TaskBaseConstants.VALID_TEAM_NAME);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> createBoardCommand.execute(parameters));
    }

    @Test
    public void execute_Should_ThrowException_When_DuplicateBoardName() {
       TaskManagementSystemRepository repository = new TaskManagementSystemRepositoryImpl();
        CreateBoardCommand createBoardCommand = new CreateBoardCommand(repository);
        List<String> parameters = new ArrayList<>();

        parameters.add(TaskBaseConstants.VALID_BOARD_NAME);
        parameters.add(TaskBaseConstants.VALID_TEAM_NAME);


        Assertions.assertThrows(IllegalArgumentException.class,
                () -> createBoardCommand.execute(parameters));

    }


}
