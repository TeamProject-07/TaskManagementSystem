package com.management.oop.project.commands.create;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Feedback;
import com.management.oop.project.models.enums.FeedbackStatusEnum;
import com.management.oop.project.utils.ParsingHelpers;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;

public class CreateFeedback implements Command {

    private String title;

    private String description;

    private int rating;

    private FeedbackStatusEnum feedbackStatusEnum;

    public static final int EXPECTED_NUMBER_OF_PARAMETERS = 4;
    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public CreateFeedback(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }


    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_PARAMETERS);
        parseParameters(parameters);
        Feedback createdFeedback = taskManagementSystemRepository.createFeedback(title, description, rating, feedbackStatusEnum);
        return String.format("Feedback with ID %d was created.", createdFeedback.getId());
    }

    private void parseParameters(List<String> parameters){
        title = parameters.get(0);
        description = parameters.get(1);
        rating = ParsingHelpers.tryParseInteger(parameters.get(2),"rating");
        feedbackStatusEnum = ParsingHelpers.tryParseEnum(parameters.get(3), FeedbackStatusEnum.class, "Error" );
    }
}
