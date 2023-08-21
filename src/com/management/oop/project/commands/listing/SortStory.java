package com.management.oop.project.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Story;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.Comparator;
import java.util.List;

public class SortStory implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS =    0;
    TaskManagementSystemRepository taskManagementSystemRepository;
    private List<Story> stories;


    public SortStory(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
        this.stories = taskManagementSystemRepository.getAllStories();
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        return stories
                .stream()
                .sorted(Comparator.comparing(Story :: getTitle).thenComparing(Story::getPriorityEnum)
                        .thenComparing(Story::getStorySizeEnum))
                .map(story -> story.getTitle() + " " + story.getPriorityEnum() + " " + story.getStorySizeEnum())
                .toList().toString();
    }

}
