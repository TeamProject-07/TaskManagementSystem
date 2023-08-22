package com.management.oop.project.commands.create;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Feedback;
import com.management.oop.project.models.enums.FeedbackStatusEnum;
import com.management.oop.project.utils.ParsingHelpers;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;

public class CreateNewFeedback implements Command {
    public static final String FEEDBACK_CREATED = "Feedback with ID %d was created.";
    private String boardName;
    private String title;

    private String description;

    private int rating;

    private FeedbackStatusEnum feedbackStatusEnum;

    public static final int EXPECTED_NUMBER_OF_PARAMETERS = 5;
    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public CreateNewFeedback(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }


    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_PARAMETERS);
        parseParameters(parameters);
        Feedback createdFeedback = taskManagementSystemRepository.createFeedback(
                boardName,
                title,
                description,
                rating,
                feedbackStatusEnum);
        return String.format(FEEDBACK_CREATED, createdFeedback.getId());
    }

    private void parseParameters(List<String> parameters) {
        boardName = parameters.get(0);
        title = parameters.get(1);
        description = parameters.get(2);
        rating = ParsingHelpers.tryParseInteger(parameters.get(3), "rating");
        feedbackStatusEnum = ParsingHelpers.tryParseEnum(parameters.get(4), FeedbackStatusEnum.class);
    }
}
