package com.management.oop.project.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Feedback;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.Comparator;
import java.util.List;

public class ListAllFeedbacks implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    TaskManagementSystemRepository taskManagementSystemRepository;
    private List<Feedback> feedbacks;

    public ListAllFeedbacks(TaskManagementSystemRepository taskManagementSystemRepository, List<Feedback> feedbacks) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
        this.feedbacks = taskManagementSystemRepository.getAllFeedback();
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String typeOfListing = parameters.get(0);
        switch (typeOfListing){
            case "list":
                return listAllFeedback();
            case "filter":
                return filter();
            case "sort":
                return sort();
            default:
                return String.format("You should choose correct type of listing.(filter/sort)");

        }
    }

    private String listAllFeedback(){
        StringBuilder list = new StringBuilder();
        for (Feedback feedback : feedbacks) {
            list.append(feedback.getId()).append(" ")
                    .append(feedback.getTitle()).append(" ")
                    .append(feedback.getDescription())
                    .append(System.lineSeparator());
        }
        return list.toString();
    }

    private String filter(){
        StringBuilder list = new StringBuilder();
        for (Feedback feedback : feedbacks) {
            list.append(feedback.getStatus()).append(System.lineSeparator());
        }
        return list.toString();
    }

    private String sort(){
        StringBuilder list = new StringBuilder();
        feedbacks.sort(Comparator.comparing(Feedback::getTitle)
                .thenComparing(Feedback::getStatus)
                .thenComparing(Feedback::getRating));
        for (Feedback feedback : feedbacks) {
            list.append(feedback.getTitle()).append(" ")
                    .append(feedback.getStatus()).append(" ")
                    .append(feedback.getRating())
                    .append(System.lineSeparator());
        }
        return list.toString();
    }

}
