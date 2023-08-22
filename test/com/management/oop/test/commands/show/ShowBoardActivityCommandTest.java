package com.management.oop.test.commands.show;
import com.management.oop.project.commands.show.ShowBoardActivityCommand;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.test.utils.TaskBaseConstants;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowBoardActivityCommandTest {

    private ShowBoardActivityCommand showBoardActivityCommand;
    private TaskManagementSystemRepository repository;
    private List<String> params = new ArrayList<>();

    @BeforeEach
    public void before() {
        repository = new TaskManagementSystemRepositoryImpl();
        showBoardActivityCommand = new ShowBoardActivityCommand(repository);
    }


    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(ShowBoardActivityCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> showBoardActivityCommand.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        params = TestUtilities.getList(0);

        Assertions.assertThrows(IllegalArgumentException.class, () -> showBoardActivityCommand.execute(params));
    }

   @Test
  public void should_ShowBoardActivity_When_ArgumentsAreValid() {
       // Arrange
       repository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);
       repository.createBoard(TaskBaseConstants.VALID_BOARD_NAME,TaskBaseConstants.VALID_TEAM_NAME);
       params.add(TaskBaseConstants.VALID_BOARD_NAME);

       // Act, Assert
      Assertions.assertDoesNotThrow(() -> showBoardActivityCommand.execute(params));
      }
}
