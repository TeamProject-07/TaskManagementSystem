package com.management.oop.project.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Task;

import java.util.Comparator;
import java.util.List;

public class SortAllTasksByTitle implements Command {

    TaskManagementSystemRepository taskManagementSystemRepository;
    private List<Task> tasks;

    public SortAllTasksByTitle(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
        this.tasks = taskManagementSystemRepository.getAllTasks();
    }

    @Override
    public String execute(List<String> parameters) {
        return sortByTitle();
    }
        private String sortByTitle(){

        return tasks.stream().sorted(Comparator.comparing(Task::getTitle))
                .map(Task::getTitle)
                .toList().toString();
    }
}
