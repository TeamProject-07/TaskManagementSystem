package com.management.oop.project.commands.create;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Bug;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.enums.BugSeverityEnum;
import com.management.oop.project.models.enums.BugStatusEnum;
import com.management.oop.project.models.enums.PriorityEnum;
import com.management.oop.project.models.tasks.BugImpl;
import com.management.oop.project.utils.ParsingHelpers;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.Arrays;
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
        String title=parameters.get(0);
        String description= parameters.get(1);
        List<String> steps= List.of(parameters.get(2).split(", "));
        PriorityEnum priorityEnum= ParsingHelpers.tryParseEnum(parameters.get(3), PriorityEnum.class);
        BugSeverityEnum severityEnum= ParsingHelpers.tryParseEnum(parameters.get(4), BugSeverityEnum.class);
        BugStatusEnum bugStatusEnum= ParsingHelpers.tryParseEnum(parameters.get(5), BugStatusEnum.class);
        return createBug(title, description, steps, priorityEnum, severityEnum, bugStatusEnum);
    }
    private String createBug(String title, String description, List<String> steps, PriorityEnum priorityEnum,
                             BugSeverityEnum bugSeverityEnum, BugStatusEnum bugStatusEnum){
        Bug createdBug=taskManagementSystemRepository.createBug(title, description, steps, priorityEnum, bugSeverityEnum, bugStatusEnum);
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
