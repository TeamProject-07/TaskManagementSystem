package com.management.oop.project.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Feedback;
import com.management.oop.project.models.enums.FeedbackStatusEnum;
import com.management.oop.project.utils.ParsingHelpers;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;
import java.util.stream.Collectors;

public class FilterFeedbackByStatus implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    TaskManagementSystemRepository taskManagementSystemRepository;
    private List<Feedback> feedbacks;

    public FilterFeedbackByStatus(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
        this.feedbacks = taskManagementSystemRepository.getAllFeedback();
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        FeedbackStatusEnum statusEnum= ParsingHelpers.tryParseEnum(parameters.get(0), FeedbackStatusEnum.class);
        return filterFeedback(statusEnum).toString();
    }

    private List<Feedback> filterFeedback(FeedbackStatusEnum statusEnum){
        return feedbacks
                .stream()
                .filter(feedback -> feedback.getStatus().equals(statusEnum))
                .collect(Collectors.toList());
    }
}
