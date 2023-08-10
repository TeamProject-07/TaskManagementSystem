package com.management.oop.project.commands.change;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Bug;
import com.management.oop.project.models.enums.BugSeverityEnum;
import com.management.oop.project.models.enums.BugStatusEnum;
import com.management.oop.project.models.enums.PriorityEnum;
import com.management.oop.project.utils.ParsingHelpers;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;

public class ChangeBugCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private TaskManagementSystemRepository taskManagementSystemRepository;

    public ChangeBugCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = ParsingHelpers.tryParseInteger(parameters.get(0), "id");
        String change = parameters.get(1);
        switch (change) {
            case "Priority":
                PriorityEnum priorityEnum = ParsingHelpers.tryParseEnum(parameters.get(1), PriorityEnum.class);
                taskManagementSystemRepository.findBugById(id).changePriorityEnum(priorityEnum);
                break;
            case "Severity":
                BugSeverityEnum bugSeverityEnum = ParsingHelpers.tryParseEnum(parameters.get(1), BugSeverityEnum.class);
                taskManagementSystemRepository.findBugById(id).changeSeverityEnum(bugSeverityEnum);
            case "advanceStatus":
                taskManagementSystemRepository.findBugById(id).advanceStatus();
            case "revertStatus":
                taskManagementSystemRepository.findBugById(id).revertStatus();
        }
        return String.format("Status changed");
    }

    private String changeBug(int id, PriorityEnum priorityEnum, String change) {
        return null;
    }
}
