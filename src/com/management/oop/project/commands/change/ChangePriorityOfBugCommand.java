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
    public static final String INVALID_VALUE_INTEGER = "Invalid value for BugID. Should be a number.";
    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public ChangePriorityOfBugCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int bugId = ParsingHelpers.tryParseInteger(parameters.get(0), INVALID_VALUE_INTEGER);
        PriorityEnum priorityEnum = ParsingHelpers.tryParseEnum(parameters.get(1).toUpperCase(), PriorityEnum.class);
        if (taskManagementSystemRepository.taskExist(bugId)){
        }
        return null;
    }
}
