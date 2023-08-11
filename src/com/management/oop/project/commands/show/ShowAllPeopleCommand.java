package com.management.oop.project.commands.show;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;

public class ShowAllPeopleCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public ShowAllPeopleCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        List<Person> people = taskManagementSystemRepository.getPeople();
        StringBuilder result = new StringBuilder();
        if (people.size() == 0) {
            throw new IllegalArgumentException("Don't have people.");
        }
        for (int i = 0; i < people.size(); i++) {
            //TODO add method split()
            result.append(String.format("%s, ", people.get(i).toString()));
        }

        return result.toString();
    }
}
