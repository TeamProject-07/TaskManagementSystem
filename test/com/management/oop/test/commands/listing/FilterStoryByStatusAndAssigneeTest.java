package com.management.oop.test.commands.listing;

import com.management.oop.project.commands.listing.FilterStoryByStatus;
import com.management.oop.project.commands.listing.FilterStoryByStatusAndAssignee;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Assignable;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.contracts.Task;
import com.management.oop.project.models.enums.PriorityEnum;
import com.management.oop.project.models.enums.StorySizeEnum;
import com.management.oop.project.models.enums.StoryStatusEnum;
import com.management.oop.test.utils.TaskBaseConstants;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FilterStoryByStatusAndAssigneeTest {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private List<String> parameters;
    private TaskManagementSystemRepository taskManagementSystemRepository;
    private FilterStoryByStatusAndAssignee filterStoryByStatusAndAssignee;
    private Task task1;
    private Task task2;

    @BeforeEach
    public void before(){
        parameters = new ArrayList<>();
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        filterStoryByStatusAndAssignee = new FilterStoryByStatusAndAssignee(taskManagementSystemRepository);
        taskManagementSystemRepository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);
        taskManagementSystemRepository.createBoard(TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TEAM_NAME);
        this.task1= taskManagementSystemRepository.createStory(TaskBaseConstants.VALID_BOARD_NAME,
                "validTitle1",
                TaskBaseConstants.VALID_DESCRIPTION,
                PriorityEnum.MEDIUM,
                StorySizeEnum.LARGE,
                StoryStatusEnum.IN_PROGRESS
        );
        this.task2=taskManagementSystemRepository.createStory(TaskBaseConstants.VALID_BOARD_NAME,
                "validTitle2",
                TaskBaseConstants.VALID_DESCRIPTION,
                PriorityEnum.MEDIUM,
                StorySizeEnum.LARGE,
                StoryStatusEnum.DONE
        );
        Assignable task=taskManagementSystemRepository.findAssignableTaskById(2);
        Person person= taskManagementSystemRepository.createPerson("personName");
        task.assignTask(person);
        System.out.println();
    }
    @Test
    public void should_ThrowException_When_ArgumentCountInvalid() {
        // Arrange
        parameters = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> filterStoryByStatusAndAssignee.execute(parameters));
    }
    @Test
    public void should_ReturnTask_WhenArgumentsAreValid(){
        //Arrange
        List<String> parameters=new ArrayList<>();
        parameters.add("personName");
        parameters.add("DONE");
        //Act
        String result=filterStoryByStatusAndAssignee.execute(parameters);
        //Assert
        Assertions.assertTrue(result.contains("validTitle2"));
    }
}
