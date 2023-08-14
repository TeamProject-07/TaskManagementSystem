package com.management.oop.test.commands.change;

import com.management.oop.project.commands.change.ChangeBugCommand;
import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.commands.show.ShowAllPeopleCommand;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
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
    public void before() {
        repository = new TaskManagementSystemRepositoryImpl();
        changeBugCommand = new ShowAllPeopleCommand(repository);
    }

    //TODO
    /*
    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeBugCommand.execute(params));
    }

     */
    @Test
    public void should_ChangeBug_When_InputIsValid() {

    }
}
