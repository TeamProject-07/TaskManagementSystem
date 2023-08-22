package com.management.oop.project.commands.change;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.enums.FeedbackStatusEnum;
import com.management.oop.project.utils.ParsingHelpers;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;

public class ChangeFeedbackCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    public static final String INVALID_TYPE = "Invalid field type %s.";
    public static final String STATUS_CHANGED = "Status was changed to %s";
    public static final String RATING_CHANGED = "Rating was changed to %d";
    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public ChangeFeedbackCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = ParsingHelpers.tryParseInteger(parameters.get(0), "id");
        String fieldType = parameters.get(1);
        String newValue = parameters.get(2);
        switch (fieldType) {
            case "status":
                return changeStatus(id, newValue);
            case "rating":
                return changeRating(id, newValue);
            default:
                return String.format(INVALID_TYPE, fieldType);
        }


    }

    private String changeStatus(int id, String newTypeOfStatus) {
        FeedbackStatusEnum feedbackStatusEnum = ParsingHelpers.tryParseEnum(newTypeOfStatus, FeedbackStatusEnum.class);
        taskManagementSystemRepository.findFeedbackById(id).changeStatus(feedbackStatusEnum);
        return String.format(STATUS_CHANGED, newTypeOfStatus);
    }

    private String changeRating(int id, String newTypeOfRating) {
        int rating = ParsingHelpers.tryParseInteger(newTypeOfRating, "rating");
        taskManagementSystemRepository.findFeedbackById(id).changeRating(rating);
        return String.format(RATING_CHANGED, rating);
    }
}
