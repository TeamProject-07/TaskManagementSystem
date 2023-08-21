package com.management.oop.test.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.commands.listing.SortFeedback;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.enums.FeedbackStatusEnum;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SortFeedbackTest {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    private List<String> parameters;
    private TaskManagementSystemRepository taskManagementSystemRepository;
    private Command sortFeedback;

    @BeforeEach
    public void before(){
        parameters = new ArrayList<>();
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        sortFeedback = new SortFeedback(taskManagementSystemRepository);
        taskManagementSystemRepository.createTeam("teamName");
        taskManagementSystemRepository.createBoard("boardName", "teamName");
        taskManagementSystemRepository.createFeedback("boardName", "validTitle",
                "validDescription", 20, FeedbackStatusEnum.DONE);
        taskManagementSystemRepository.createFeedback("boardName", "validTitle",
                "validDescription", 20, FeedbackStatusEnum.DONE);

    }
    @Test
    public void should_ThrowException_When_ArgumentCountInvalid() {
        // Arrange
        parameters = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS + 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> sortFeedback.execute(parameters));
    }
}
