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

    public SortFeedback(TaskManagementSystemRepository taskManagementSystemRepository, List<Feedback> feedbacks) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
        this.feedbacks = feedbacks;
    }

    @Override
    public String execute(List<String> parameters) {
        return sortFeedback();
    }

    private String sortFeedback(){
//        StringBuilder list = new StringBuilder();
//        feedbacks.sort(Comparator.comparing(Feedback::getTitle)
//                .thenComparing(Feedback::getRating));
//        for (Feedback feedback : feedbacks) {
//            list.append(feedback.getTitle()).append(" ")
//                    .append(feedback.getRating())
//                    .append(System.lineSeparator());
//        }
//        return list.toString();
        return feedbacks
                .stream()
                .sorted(Comparator.comparing(Feedback :: getTitle).thenComparing(Feedback::getRating))
                .map(feedback -> feedback.getTitle() + " " + feedback.getRating())
                .collect(Collectors.toList()).toString();
    }
}
