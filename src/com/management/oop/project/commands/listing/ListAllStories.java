package com.management.oop.project.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Feedback;
import com.management.oop.project.models.contracts.Story;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.Comparator;
import java.util.List;

public class ListAllStories implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    TaskManagementSystemRepository taskManagementSystemRepository;
    private  List<Story>stories;

    public ListAllStories(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
        this.stories=taskManagementSystemRepository.getAllStories();

    }
    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String typeOfListing = parameters.get(0);
        switch (typeOfListing){
            case "list":
                return listAllStories();
            case "filter":
                return filter();
            case "sort":
                return sort();
            default:
                return String.format("You should choose correct type of listing.(filter/sort)");
        }
    }
    private String listAllStories(){
        StringBuilder list = new StringBuilder();
        for (Story story : stories) {
            list.append(story.getId()).append(" ")
                    .append(story.getTitle()).append(" ")
                    .append(story.getDescription())
                    .append(System.lineSeparator());
        }
        return list.toString();
    }

    private String filter(){
        StringBuilder list = new StringBuilder();
        for (Story story : stories) {
            list.append(story.getStoryStatusEnum()).append(System.lineSeparator());
        }
        return list.toString();
    }

    private String sort(){
        StringBuilder list = new StringBuilder();
        stories.sort(Comparator.comparing(Story::getTitle)
                .thenComparing(Story::getPriorityEnum)
                .thenComparing(Story::getStorySizeEnum));
        for (Story story : stories) {
            list.append(story.getTitle()).append(" ")
                    .append(story.getPriorityEnum()).append(" ")
                    .append(story.getStorySizeEnum())
                    .append(System.lineSeparator());
        }
        return list.toString();
    }
}
