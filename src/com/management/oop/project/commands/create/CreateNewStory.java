package com.management.oop.project.commands.create;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Story;
import com.management.oop.project.models.enums.PriorityEnum;
import com.management.oop.project.models.enums.StorySizeEnum;
import com.management.oop.project.models.enums.StoryStatusEnum;
import com.management.oop.project.utils.ParsingHelpers;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;

public class CreateNewStory implements Command {
    public static final String STORY_CREATED = "Story with ID %d was created.";
    private String boardName;
    private String title;
    private String description;
    private PriorityEnum priority;
    private StorySizeEnum size;
    private StoryStatusEnum status;

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 6;
    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public CreateNewStory(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);
        Story createdStory = taskManagementSystemRepository.createStory(
                boardName,
                title,
                description,
                priority,
                size,
                status);
        return String.format(STORY_CREATED, createdStory.getId());
    }

    private void parseParameters(List<String> parameters) {
        boardName = parameters.get(0);
        title = parameters.get(1);
        description = parameters.get(2);
        priority = ParsingHelpers.tryParseEnum(parameters.get(3), PriorityEnum.class);
        size = ParsingHelpers.tryParseEnum(parameters.get(4), StorySizeEnum.class);
        status = ParsingHelpers.tryParseEnum(parameters.get(5), StoryStatusEnum.class);
    }
}
