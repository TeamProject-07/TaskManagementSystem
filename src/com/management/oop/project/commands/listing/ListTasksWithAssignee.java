package com.management.oop.project.commands.listing;

import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Task;

import java.util.List;

public class ListTasksWithAssignee {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    TaskManagementSystemRepository taskManagementSystemRepository;
    private List<Task> tasks;

    public ListTasksWithAssignee(TaskManagementSystemRepository taskManagementSystemRepository, List<Task> tasks) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
        this.tasks = taskManagementSystemRepository.getAllTasks();
    }
}
