package com.management.oop.project.commands.assign;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Task;
import com.management.oop.project.utils.ParsingHelpers;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;

public class UnassignTaskToPersonCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public UnassignTaskToPersonCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String personName = parameters.get(0);
        int id = ParsingHelpers.tryParseInteger(parameters.get(1), "id");
        return unAssignTask(personName, id);
    }

    private String unAssignTask(String personName, int id) {
        if (taskManagementSystemRepository.taskExist(id)) {
            Task task = taskManagementSystemRepository.findTaskById(id);
            taskManagementSystemRepository.findPersonByName(personName).unAssignTask(task);
            return String.format("Task with id %d was removed", id);
        }
        throw new IllegalArgumentException("Task doesn't exist.");
    }
}
