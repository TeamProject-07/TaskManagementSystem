package com.management.oop.project.commands.add;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;

public class AddPersonToTeamCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String PERSON_ADDED_TO_TEAM = "Person % was added to team";
    private final TaskManagementSystemRepository taskManagementSystemRepository;

//    public AddPersonToTeamCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
//        this.taskManagementSystemRepository = taskManagementSystemRepository;
//    }
//
//    @Override
//    public String execute(List<String> parameters) {
//        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
//            String name= parameters.get(0);
//            Person createdPerson=taskManagementSystemRepository.createPerson(name);
//            return String.format(PERSON_ADDED_TO_TEAM, name);
//    }
}
