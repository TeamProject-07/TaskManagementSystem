package com.management.oop.project.commands.show;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.EventLog;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;

public class ShowPersonActivityCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String SHOW_PERSON_ACTIVITY = "Show %s activity:";
    public static final String DO_NOT_ACTIVITY = "Don't have activity.";
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

    private String getPersonActivity(String personName) {
        Person person = taskManagementSystemRepository.findPersonByName(personName);
        List<EventLog> histories = person.getHistory();
        StringBuilder result = new StringBuilder();
        result.append(String.format(SHOW_PERSON_ACTIVITY, personName)).append(System.lineSeparator());
        if (histories.size() == 0) {
            throw new IllegalArgumentException(DO_NOT_ACTIVITY);
        }

        for (EventLog history : histories) {
            result.append(String.format("%s%n", history));
        }
        return result.toString();
    }
}
