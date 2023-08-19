package com.management.oop.test.commands.listing;

import com.management.oop.project.commands.listing.FilterAllTasks;
import com.management.oop.project.commands.listing.FilterStoryByStatus;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Story;
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

public class FilterStoryByStatusTest {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private List<String> parameters;
    private TaskManagementSystemRepository repository;
    private FilterStoryByStatus filterStoryByStatus;

    @BeforeEach
    public void before(){
        parameters = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
        filterStoryByStatus = new FilterStoryByStatus(repository);
    }
    @Test
    public void should_ThrowException_When_ArgumentCountInvalid() {
        // Arrange
        parameters = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> filterStoryByStatus.execute(parameters));
    }
    @Test
    public void should_CreateStory_WhenStatus_AreValid() {
    }
}
