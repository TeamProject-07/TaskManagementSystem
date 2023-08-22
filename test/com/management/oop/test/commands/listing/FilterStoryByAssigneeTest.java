package com.management.oop.test.commands.listing;

import com.management.oop.project.commands.listing.FilterStoryByAssignee;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.contracts.Story;
import com.management.oop.project.models.enums.PriorityEnum;
import com.management.oop.project.models.enums.StorySizeEnum;
import com.management.oop.project.models.enums.StoryStatusEnum;
import com.management.oop.project.utils.ListingHelpers;
import com.management.oop.test.utils.TaskBaseConstants;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FilterStoryByAssigneeTest {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS =     1;
    private List<String> parameters;

    private Person person;
    private Story story;
    private TaskManagementSystemRepository taskManagementSystemRepository;

    private FilterStoryByAssignee filterStoryByAssignee;

    @BeforeEach
    public void beforeEach(){
        parameters = new ArrayList<>();
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        filterStoryByAssignee = new FilterStoryByAssignee(taskManagementSystemRepository);

        person = taskManagementSystemRepository.createPerson("validName");

        taskManagementSystemRepository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);
        taskManagementSystemRepository.createBoard(TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TEAM_NAME);
        story = taskManagementSystemRepository.createStory(TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                PriorityEnum.MEDIUM,
                StorySizeEnum.LARGE,
                StoryStatusEnum.IN_PROGRESS);
        story.assignTask(person);

    }
    @Test
    public void should_ThrowException_When_ArgumentCountInvalid() {
        // Arrange
        parameters = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> filterStoryByAssignee.execute(parameters));
    }
  @Test
  public void should_ReturnStory_WhenParameters_AreValid() {
      parameters.add("validName");
      String result = filterStoryByAssignee.execute(parameters);
      //Assert
      Assertions.assertEquals(ListingHelpers.getAsString(taskManagementSystemRepository.getAllStories()), result);
  }
}
