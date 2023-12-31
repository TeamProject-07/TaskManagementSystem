package com.management.oop.project.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Feedback;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.Comparator;
import java.util.List;

public class SortFeedback implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public SortFeedback(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        return taskManagementSystemRepository.getAllFeedback()
                .stream()
                .sorted(Comparator.comparing(Feedback::getTitle).thenComparing(Feedback::getRating))
                .map(feedback -> feedback.getTitle() + " " + feedback.getRating())
                .toList().toString();
    }
}
