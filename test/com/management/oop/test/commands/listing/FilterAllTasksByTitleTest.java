package com.management.oop.test.commands.listing;
import com.management.oop.project.commands.listing.FilterAllTasksByTitle;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class FilterAllTasksByTitleTest {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS =  1;

    private List<String> parameters;
    private TaskManagementSystemRepository taskManagementSystemRepository;
    private FilterAllTasksByTitle filterAllTasks;

    @BeforeEach
    public void before() {
        parameters = new ArrayList<>();
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        filterAllTasks = new FilterAllTasksByTitle(taskManagementSystemRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountInvalid() {
        // Arrange
        parameters = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> filterAllTasks.execute(parameters));
    }
}
