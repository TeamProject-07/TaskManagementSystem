package com.management.oop.project.commands.create;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Bug;
import com.management.oop.project.models.enums.BugSeverityEnum;
import com.management.oop.project.models.enums.PriorityEnum;
import com.management.oop.project.utils.ParsingHelpers;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;

public class CreateNewBug implements Command {
    public static final int EXPECTED_NUMBER_OF_PARAMETERS = 5;
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
        return createBug(boardName, title, description, steps, priorityEnum, severityEnum);
    }
    private String createBug(String boardName, String title, String description, List<String> steps, PriorityEnum priorityEnum,
                             BugSeverityEnum bugSeverityEnum){
        Bug createdBug=taskManagementSystemRepository.createBug(boardName, title, description, steps, priorityEnum, bugSeverityEnum);
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
