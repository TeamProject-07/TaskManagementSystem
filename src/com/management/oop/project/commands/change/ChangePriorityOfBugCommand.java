package com.management.oop.project.commands.change;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.enums.PriorityEnum;
import com.management.oop.project.utils.ParsingHelpers;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;

public class ChangePriorityOfBugCommand implements Command {
public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    public static final String NOT_PRIORITY = "This is not priority.";
    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public ChangePriorityOfBugCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String bugId = parameters.get(0);
        PriorityEnum priorityEnum = ParsingHelpers.tryParseEnum(parameters.get(1).toUpperCase(), PriorityEnum.class, NOT_PRIORITY);
        return null;
    }
}
