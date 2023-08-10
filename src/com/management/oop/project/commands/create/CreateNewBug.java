package com.management.oop.project.commands.create;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Bug;
import com.management.oop.project.models.enums.BugSeverityEnum;
import com.management.oop.project.models.enums.BugStatusEnum;
import com.management.oop.project.models.enums.PriorityEnum;
import com.management.oop.project.utils.ParsingHelpers;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;

public class CreateNewBug implements Command {
    public static final int EXPECTED_NUMBER_OF_PARAMETERS = 6;
    private TaskManagementSystemRepository taskManagementSystemRepository;

    public CreateNewBug(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_PARAMETERS);
        String boardName=parameters.get(0);
        String title=parameters.get(1);
        String description= parameters.get(2);
        List<String> steps= List.of(parameters.get(3).split(", "));
        PriorityEnum priorityEnum= ParsingHelpers.tryParseEnum(parameters.get(4), PriorityEnum.class);
        BugSeverityEnum severityEnum= ParsingHelpers.tryParseEnum(parameters.get(5), BugSeverityEnum.class);
        BugStatusEnum bugStatusEnum= ParsingHelpers.tryParseEnum(parameters.get(6), BugStatusEnum.class);
        return createBug(boardName, title, description, steps, priorityEnum, severityEnum, bugStatusEnum);
    }
    private String createBug(String boardName, String title, String description, List<String> steps, PriorityEnum priorityEnum,
                             BugSeverityEnum bugSeverityEnum, BugStatusEnum bugStatusEnum){
        Bug createdBug=taskManagementSystemRepository.createBug(boardName, title, description, steps, priorityEnum, bugSeverityEnum, bugStatusEnum);
        return String.format("Bug was created.");
    }
}

//    String title,
//    String description,
//        List<String> steps,
//        PriorityEnum priorityEnum,
//        BugSeverityEnum bugSeverityEnum,
//        BugStatusEnum bugStatusEnum,
//        Person assignee
