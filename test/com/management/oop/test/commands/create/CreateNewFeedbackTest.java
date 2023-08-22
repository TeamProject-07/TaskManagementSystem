package com.management.oop.test.commands.create;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.commands.create.CreateNewFeedback;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CreateNewFeedbackTest {

    public static final int EXPECTED_NUMBER_OF_PARAMETERS = 5;
    private TaskManagementSystemRepository taskManagementSystemRepository;
    private Command createNewFeedback;

    @BeforeEach
    public void before() {
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        createNewFeedback = new CreateNewFeedback(taskManagementSystemRepository);
        taskManagementSystemRepository.createTeam("teamName");
        taskManagementSystemRepository.createBoard("validName", "teamName");
    }

    @Test
    public void should_ThrowException_When_ArgumentCountInvalid() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_PARAMETERS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createNewFeedback.execute(params));
    }

    @Test
    public void should_CreateFeedback_WhenArguments_AreValid() {
        //Arrange
        List<String> params = List.of(
                "validName",
                "validTitleFeedback",
                "validDescription",
                "20",
                "new");
        // Act
        String result  = createNewFeedback.execute(params);
        // Assert
        Assertions.assertEquals("Feedback with ID 1 was created.", result);
        Assertions.assertEquals(taskManagementSystemRepository.getAllFeedback().size(), 1);
    }
}
