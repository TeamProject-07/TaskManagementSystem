package com.management.oop.project.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Feedback;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortFeedback implements Command {

    TaskManagementSystemRepository taskManagementSystemRepository;
    private List<Feedback> feedbacks;

    public SortFeedback(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
        this.feedbacks = taskManagementSystemRepository.getAllFeedback();
    }

    @Override
    public String execute(List<String> parameters) {
        return sortFeedback();
    }

    private String sortFeedback(){
        return feedbacks
                .stream()
                .sorted(Comparator.comparing(Feedback :: getTitle).thenComparing(Feedback::getRating))
                .map(feedback -> feedback.getTitle() + " " + feedback.getRating())
                .toList().toString();
    }
}
