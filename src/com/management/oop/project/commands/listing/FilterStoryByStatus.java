package com.management.oop.project.commands.listing;
import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Story;
import com.management.oop.project.models.enums.StoryStatusEnum;
import com.management.oop.project.utils.ParsingHelpers;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;
import java.util.stream.Collectors;

public class FilterStoryByStatus implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    TaskManagementSystemRepository taskManagementSystemRepository;

    private List<Story> stories;

    public FilterStoryByStatus(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
        this.stories = taskManagementSystemRepository.getAllStories();
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        StoryStatusEnum statusEnum = ParsingHelpers.tryParseEnum(parameters.get(0), StoryStatusEnum.class);
        return filterStory(statusEnum).toString();
    }

    private List<Story> filterStory(StoryStatusEnum statusEnum){
        return stories
                .stream()
                .filter(story -> story.getStoryStatusEnum().equals(statusEnum))
                .collect(Collectors.toList());
    }
}
