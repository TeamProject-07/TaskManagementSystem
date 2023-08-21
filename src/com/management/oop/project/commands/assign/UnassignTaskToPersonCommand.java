package com.management.oop.project.commands.assign;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Assignable;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.contracts.Task;
import com.management.oop.project.utils.ParsingHelpers;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;

public class UnassignTaskToPersonCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String TASK_EXIST = "Task with ID %d was unassigned from person with name %s.";
    public static final String TASK_DOES_NOT_EXIST = "There is no assigned task to person with name: %s.";

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
        if (taskManagementSystemRepository.taskExist(id) && taskManagementSystemRepository.ifTaskIsAssignable(id)) {
            Assignable task = taskManagementSystemRepository.findAssignableTaskById(id);
            if (task.getAssignee() != null) {
                Person person = taskManagementSystemRepository.findPersonByName(personName);
                task.unAssignTask(person);
                return String.format(TASK_EXIST, id, personName);
            }
        }
        throw new IllegalArgumentException(String.format(TASK_DOES_NOT_EXIST, personName));
    }
}
