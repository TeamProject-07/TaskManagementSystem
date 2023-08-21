package com.management.oop.test.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.commands.listing.SortAllTasksByTitle;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Board;
import com.management.oop.project.models.contracts.Task;
import com.management.oop.project.models.contracts.Team;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SortAllTasksByTitleTest {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    private TaskManagementSystemRepository taskManagementSystemRepository;

    private Command sortAllTasksByTitle;

    private Task task;

    private List<Task> tasks;

    private List<String> parameters;

    private Board board;

    private Team team;


    @BeforeEach
    public void before() {
        parameters = new ArrayList<>();
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        sortAllTasksByTitle = new SortAllTasksByTitle(taskManagementSystemRepository);

    }

    @Test
    public void should_ThrowException_When_ArgumentCountInvalid() {
    //Arrange
        parameters = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS + 1);

    //Act,Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> sortAllTasksByTitle.execute(parameters));
    }

}