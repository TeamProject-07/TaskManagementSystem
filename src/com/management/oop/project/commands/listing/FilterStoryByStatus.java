package com.management.oop.project.commands.listing;
import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Story;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;
import java.util.stream.Collectors;

public class FilterStoryByStatus implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    TaskManagementSystemRepository taskManagementSystemRepository;

    private List<Story> stories;

    public FilterStoryByStatus(TaskManagementSystemRepository taskManagementSystemRepository, List<Story> stories) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
        this.stories = stories;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String parameter = parameters.get(0);
        return filterStory(parameter).toString();
    }

    private List<Story> filterStory(String targetStatus){
        return stories
                .stream()
                .filter(story -> story.getStoryStatusEnum().equals(targetStatus))
                .collect(Collectors.toList());
    }
}
