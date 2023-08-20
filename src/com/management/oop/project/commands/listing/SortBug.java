package com.management.oop.project.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Bug;

import java.util.Comparator;
import java.util.List;

public class SortBug implements Command {
    TaskManagementSystemRepository taskManagementSystemRepository;
    private List<Bug> bugs;

    public SortBug(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        return bugs
                .stream()
                .sorted(Comparator.comparing(Bug::getTitle).thenComparing(Bug::getBugPriorityEnum)
                        .thenComparing(Bug::getBugSeverityEnum))
                .map(bug -> bug.getTitle() + " " + bug.getBugPriorityEnum() + " " + bug.getBugSeverityEnum())
                .toList().toString();
    }
}

