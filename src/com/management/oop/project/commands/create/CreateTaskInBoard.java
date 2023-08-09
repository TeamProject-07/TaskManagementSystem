package com.management.oop.project.commands.create;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;

import java.util.List;

public class CreateTaskInBoard implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public CreateTaskInBoard(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        return null;
    }
}
