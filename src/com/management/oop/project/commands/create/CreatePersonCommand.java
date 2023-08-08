package com.management.oop.project.commands.create;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;

public class CreatePersonCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String PERSON_CREATED = "Person with name %s was created.";
    public static final String PERSON_EXISTS_ERROR = "Person with name %s already exists";
    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public CreatePersonCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String personName = parameters.get(0);
        return createPerson(personName);
    }

    private String createPerson(String personName) {
        if (taskManagementSystemRepository.personExist(personName)) {
            throw new IllegalArgumentException(String.format(PERSON_EXISTS_ERROR, personName));
        }
        taskManagementSystemRepository.createPerson(personName);
        return String.format(PERSON_CREATED, personName);
    }
}
