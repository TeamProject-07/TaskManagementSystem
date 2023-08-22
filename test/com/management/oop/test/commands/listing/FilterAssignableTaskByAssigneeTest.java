package com.management.oop.test.commands.listing;

import com.management.oop.project.commands.listing.FilterAssignableTaskByAssignee;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Bug;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.enums.BugSeverityEnum;
import com.management.oop.project.models.enums.PriorityEnum;
import com.management.oop.project.utils.ListingHelpers;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class FilterAssignableTaskByAssigneeTest {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS =     1;

    private FilterAssignableTaskByAssignee filterAssignableTaskByAssignee;

    private TaskManagementSystemRepository taskManagementSystemRepository;

    private List<String> parameters;

    private Bug bug;

    private Person person;


    @BeforeEach
    public void beforeEach() {
        parameters = new ArrayList<>();
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        filterAssignableTaskByAssignee = new FilterAssignableTaskByAssignee(taskManagementSystemRepository);
        person = taskManagementSystemRepository.createPerson("validName");
        taskManagementSystemRepository.createTeam("teamName");
        taskManagementSystemRepository.createBoard("boardName", "teamName");
        bug = taskManagementSystemRepository.createBug("boardName","validBugTitle", "validDescription",
                asList("step1", "step2"), PriorityEnum.HIGH, BugSeverityEnum.MAJOR );
        bug.assignTask(person);

    }


    @Test
    public void should_ThrowException_When_ArgumentCountInvalid() {
        // Arrange
        parameters = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> filterAssignableTaskByAssignee.execute(parameters));
    }

    @Test
    public void should_ReturnBug_WhenParameters_AreValid() {
        //Arrange
        parameters.add("validName");
        //Act
        String result = filterAssignableTaskByAssignee.execute(parameters);
        //Assert
        Assertions.assertEquals(ListingHelpers.getAsString(taskManagementSystemRepository.getAllAssignableTasks()), result);
    }


}

