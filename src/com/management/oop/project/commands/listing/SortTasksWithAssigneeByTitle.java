package com.management.oop.project.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Task;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.Comparator;
import java.util.List;

public class SortTasksWithAssigneeByTitle implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    private TaskManagementSystemRepository taskManagementSystemRepository;

    public SortTasksWithAssigneeByTitle(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        return taskManagementSystemRepository.getAllAssignableTasks()
                .stream()
                .sorted(Comparator.comparing(Task::getTitle))
                .map(Task::getTitle)
                .toList().toString();
    }
}



