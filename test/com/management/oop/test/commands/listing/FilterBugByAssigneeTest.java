package com.management.oop.test.commands.listing;

import com.management.oop.project.commands.listing.FilterBugByAssignee;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Assignable;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.enums.BugSeverityEnum;
import com.management.oop.project.models.enums.PriorityEnum;
import com.management.oop.test.utils.TaskBaseConstants;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FilterBugByAssigneeTest {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private List<String> parameters;
    private List<String> steps;
    private TaskManagementSystemRepository taskManagementSystemRepository;
    private FilterBugByAssignee filterBugByAssignee;

    @BeforeEach
    public void before() {
        parameters = new ArrayList<>();
        steps = new ArrayList<>();
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        filterBugByAssignee = new FilterBugByAssignee(taskManagementSystemRepository);
        taskManagementSystemRepository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);
        taskManagementSystemRepository.createBoard(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TEAM_NAME);
        taskManagementSystemRepository.createBug(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TITLE, "validDescription",
                steps, PriorityEnum.HIGH, BugSeverityEnum.MAJOR);
        taskManagementSystemRepository.createBug(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TITLE_1,
                TaskBaseConstants.VALID_DESCRIPTION,
                steps, PriorityEnum.HIGH,
                BugSeverityEnum.MAJOR);
        Assignable task = taskManagementSystemRepository.findAssignableTaskById(2);
        Person person = taskManagementSystemRepository.createPerson(TaskBaseConstants.VALID_PERSON_NAME);
        task.assignTask(person);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountInvalid() {
        // Arrange
        parameters = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> filterBugByAssignee.execute(parameters));
    }

    @Test
    public void should_ReturnTask_WhenArgumentsAreValid() {
        //Arrange
        List<String> parameters = new ArrayList<>();
        parameters.add(TaskBaseConstants.VALID_PERSON_NAME);
        //Act
        String result = filterBugByAssignee.execute(parameters);
        //Assert
        Assertions.assertTrue(result.contains(TaskBaseConstants.VALID_TITLE_1));
    }
}
