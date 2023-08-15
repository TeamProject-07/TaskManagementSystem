package com.management.oop.test.commands.create;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.commands.create.CreatePersonCommand;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.test.models.BugImplTests;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateNewBugTest {

    public static final int EXPECTED_NUMBER_OF_params = 5;

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

 // @Test
 // public void execute_Should_ThrowException_When_PassengerCapacityNotNumber() {
 //     // Arrange
 //     List<String> params = List.of(
 //                   "invalid",
 //             String.valueOf(BugImplTests.steps),
 //             "true");
 //     // Act, Assert
 //     Assertions.assertThrows(InvalidUserInputException.class, () -> command.execute(params));
 // }
//
  // @Test
  // public void execute_Should_ThrowException_When_PricePerKmNotNumber() {
  //     // Arrange
  //     List<String> params = List.of(
  //             String.valueOf(AirplaneTests.VALID_AIRPLANE_PASSENGER_CAPACITY),
  //             "invalid",
  //             "true");

  //     // Act, Assert
  //     Assertions.assertThrows(InvalidUserInputException.class, () -> command.execute(params));
  // }

  // @Test
  // public void execute_Should_ThrowException_When_HasFreeFoodNotBoolean() {
  //     // Arrange
  //     List<String> params = List.of(
  //             String.valueOf(AirplaneTests.VALID_AIRPLANE_PASSENGER_CAPACITY),
  //             String.valueOf(AirplaneTests.VALID_AIRPLANE_PRICE),
  //             "invalid");

  //     // Act, Assert
  //     Assertions.assertThrows(InvalidUserInputException.class, () -> command.execute(params));
  // }

  // @Test
  // public void execute_Should_AddNewAirplane_When_PassedValidInput() {
  //     // Arrange
  //     List<String> params = List.of(
  //             String.valueOf(AirplaneTests.VALID_AIRPLANE_PASSENGER_CAPACITY),
  //             String.valueOf(AirplaneTests.VALID_AIRPLANE_PRICE),
  //             "true");

  //     // Act
  //     command.execute(params);

  //     // Assert
  //     Assertions.assertEquals(1, repository.getVehicles().size());
  // }

}
