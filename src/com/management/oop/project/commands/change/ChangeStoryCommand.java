package com.management.oop.project.commands.change;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.enums.*;
import com.management.oop.project.utils.ParsingHelpers;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;

public class ChangeStoryCommand implements Command {
    //Priority, Size, Status
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;
    private TaskManagementSystemRepository taskManagementSystemRepository;

    public ChangeStoryCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = ParsingHelpers.tryParseInteger(parameters.get(0), "id");
        String fieldType = parameters.get(1);
        String newValue=parameters.get(2);
        switch (fieldType) {
            case "priority":
                return changePriority(id, newValue);
            case "size":
                return changeSize(id, newValue);
            case "status":
                return changeStatus(id, newValue);
            default:
                return String.format("Invalid field type %s.", fieldType);
        }
    }


    private String changePriority(int id, String newTypeOfPriority) {
        PriorityEnum priorityEnum = ParsingHelpers.tryParseEnum(newTypeOfPriority, PriorityEnum.class);
        taskManagementSystemRepository.findStoryById(id).changePriorityEnum(priorityEnum);
        return String.format("Priority was changed to %s.", newTypeOfPriority);
    }
    private String changeSize(int id, String newTypeOfSize) {
        StorySizeEnum storySizeEnum = ParsingHelpers.tryParseEnum(newTypeOfSize, StorySizeEnum.class);
        taskManagementSystemRepository.findStoryById(id).changeSize(storySizeEnum);
        return String.format("Size was changed to %s.", newTypeOfSize);
    }
    private String changeStatus(int id, String newTypeOfStatus) {
        StoryStatusEnum storyStatusEnum= ParsingHelpers.tryParseEnum(newTypeOfStatus, StoryStatusEnum.class);
        taskManagementSystemRepository.findStoryById(id).changeStoryStatusEnum(storyStatusEnum);
        return String.format("Status was changed to %s.", newTypeOfStatus);
    }
}
