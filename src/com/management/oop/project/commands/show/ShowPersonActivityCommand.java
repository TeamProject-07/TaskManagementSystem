package com.management.oop.project.commands.show;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.History;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;

public class ShowPersonActivityCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public ShowPersonActivityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String personName = parameters.get(0);
        return getPersonActivity(personName);
    }

    private String getPersonActivity(String personName){
        Person person = taskManagementSystemRepository.findPersonByName(personName);
        List<History> histories = taskManagementSystemRepository.getHistory();
        StringBuilder result = new StringBuilder();
        if (histories.size() <= 0){
            throw new IllegalArgumentException("Don't have activity.");
        }
        for (int i = 0; i < histories.size(); i++) {
            //TODO ADD METHOD SPLIT()
            result.append(String.format("%s ", histories.get(i).toString()));
        }
        return result.toString();
    }
}
