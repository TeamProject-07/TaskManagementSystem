package com.management.oop.project.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Task;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;

public class FilterAllTasks implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private TaskManagementSystemRepository taskManagementSystemRepository;
    private List<Task> tasks;

    public FilterAllTasks(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
        this.tasks = taskManagementSystemRepository.getAllTasks();
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String keyword=parameters.get(0);
        return filterByTitle(keyword);
    }

    private String filterByTitle(String keyword){
        return tasks
                .stream()
                .filter(task -> task.getTitle().contains(keyword))
                .toList()
                .toString();
    }
}
