package com.management.oop.test.commands.create;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.commands.create.CreatePersonCommand;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.test.models.PersonImplTests;
import com.management.oop.test.utils.TaskBaseConstants;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreatePersonCommandTest {
    public static final int EXPECTED_NUMBER_OF_params = 1;

    private Command command;
    private TaskManagementSystemRepository repository;

    @BeforeEach
    public void before() {
        this.repository = new TaskManagementSystemRepositoryImpl();
        this.command = new CreatePersonCommand(repository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_params - 1);

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

 @Test
  public void execute_Should_AddNewAirplane_When_PassedValidInput() {
      // Arrange
      List<String> params = List.of(
              String.valueOf(TaskBaseConstants.VALID_PERSON_NAME));
              command.execute(params);

     // Assert
     Assertions.assertEquals(1, repository.getPeople().size());

    }
}
