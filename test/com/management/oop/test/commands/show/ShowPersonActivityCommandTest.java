package com.management.oop.test.commands.show;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.commands.show.ShowPersonActivityCommand;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.test.utils.TaskBaseConstants;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ShowPersonActivityCommandTest {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private TaskManagementSystemRepository taskManagementSystemRepository;
    private Command showPersonActivity;

    @BeforeEach
    public void before() {
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        showPersonActivity = new ShowPersonActivityCommand(taskManagementSystemRepository);
        taskManagementSystemRepository.createPerson(TaskBaseConstants.VALID_PERSON_NAME);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountInvalid() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> showPersonActivity.execute(params));
    }

    @Test
    public void should_ThrowException_WhenPerson_DoesNotExist() {
        //Arrange
        List<String> params = List.of(TaskBaseConstants.INVALID_PERSON_NAME);
        //Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> showPersonActivity.execute(params));
    }

    @Test
    public void should_OutputMessageWhen_PersonDoesHas_Activity() {
        //Arrange
        List<String> params = List.of(TaskBaseConstants.VALID_PERSON_NAME);
        //Act
        String result = showPersonActivity.execute(params);
        //Assert
        Assertions.assertTrue(result.contains(TaskBaseConstants.VALID_PERSON_NAME));
    }
}
