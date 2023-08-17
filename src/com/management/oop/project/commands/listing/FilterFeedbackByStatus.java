package com.management.oop.project.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Feedback;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;
import java.util.stream.Collectors;

public class FilterFeedbackByStatus implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    TaskManagementSystemRepository taskManagementSystemRepository;
    private List<Feedback> feedbacks;

    public FilterFeedbackByStatus(TaskManagementSystemRepository taskManagementSystemRepository, List<Feedback> feedbacks) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
        this.feedbacks = feedbacks;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String parameter = parameters.get(0);
        return filterFeedback(parameter).toString();
    }

    private List<Feedback> filterFeedback(String status){
        return feedbacks
                .stream()
                .filter(feedback -> feedback.getStatus().equals(status))
                .collect(Collectors.toList());
    }
}
