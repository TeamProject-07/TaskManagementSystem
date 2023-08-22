package com.management.oop.project.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Task;
import com.management.oop.project.utils.ListingHelpers;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;

public class FilterAllTasksByTitle implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private TaskManagementSystemRepository taskManagementSystemRepository;

    public FilterAllTasksByTitle(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String keyword=parameters.get(0);
        return filterByTitle(keyword);
    }

    private String filterByTitle(String keyword){
        return ListingHelpers.getAsString(taskManagementSystemRepository.getAllTasks()
                .stream()
                .filter(task -> task.getTitle().contains(keyword))
                .toList());
    }
}
