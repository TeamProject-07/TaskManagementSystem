package com.management.oop.project.commands.change;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.enums.BugSeverityEnum;
import com.management.oop.project.models.enums.BugStatusEnum;
import com.management.oop.project.models.enums.PriorityEnum;
import com.management.oop.project.utils.ParsingHelpers;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;

public class ChangeBugCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    public static final String PRIORITY_CHANGED = "Priority was changed to %s.";
    public static final String SEVERITY_CHANGED = "Severity was changed to %s.";
    public static final String STATUS_CHANGED = "Status was changed to %s.";
    public static final String INVALID_TYPE = "Invalid field type %s.";
    private TaskManagementSystemRepository taskManagementSystemRepository;

    public ChangeBugCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = ParsingHelpers.tryParseInteger(parameters.get(0), "id");
        String fieldType = parameters.get(1);
        String newValue = parameters.get(2);
        switch (fieldType) {
            case "priority":
                return changePriority(id, newValue);
            case "severity":
                return changeSeverity(id, newValue);
            case "status":
                return changeStatus(id, newValue);
            default:
                return String.format(INVALID_TYPE, fieldType);
        }
    }

    private String changePriority(int id, String newTypeOfPriority) {
        PriorityEnum priorityEnum = ParsingHelpers.tryParseEnum(newTypeOfPriority, PriorityEnum.class);
        taskManagementSystemRepository.findBugById(id).changePriorityEnum(priorityEnum);
        return String.format(PRIORITY_CHANGED, newTypeOfPriority);
    }

    private String changeSeverity(int id, String newTypeOfSeverity) {
        BugSeverityEnum bugSeverityEnum = ParsingHelpers.tryParseEnum(newTypeOfSeverity, BugSeverityEnum.class);
        taskManagementSystemRepository.findBugById(id).changeSeverityEnum(bugSeverityEnum);
        return String.format(SEVERITY_CHANGED, newTypeOfSeverity);
    }

    private String changeStatus(int id, String newTypeOfStatus) {
        BugStatusEnum bugStatusEnum = ParsingHelpers.tryParseEnum(newTypeOfStatus, BugStatusEnum.class);
        taskManagementSystemRepository.findBugById(id).changeStatus(bugStatusEnum);
        return String.format(STATUS_CHANGED, newTypeOfStatus);
    }
}

