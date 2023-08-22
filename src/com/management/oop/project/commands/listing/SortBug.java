package com.management.oop.project.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Bug;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.Comparator;
import java.util.List;

public class SortBug implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public SortBug(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        return taskManagementSystemRepository.getAllBugs()
                .stream()
                .sorted(Comparator.comparing(Bug::getTitle).thenComparing(Bug::getBugPriorityEnum)
                        .thenComparing(Bug::getBugSeverityEnum))
                .map(bug -> bug.getTitle() + " " + bug.getBugPriorityEnum() + " " + bug.getBugSeverityEnum())
                .toList().toString();
    }
}

