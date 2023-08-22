package com.management.oop.project.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Assignable;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.utils.ListingHelpers;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;
import java.util.stream.Collectors;

public class FilterAssignableTaskByAssignee implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public FilterAssignableTaskByAssignee(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String personName = parameters.get(0);
        Person person = taskManagementSystemRepository.findPersonByName(personName);
        return ListingHelpers.getAsString(filterAssignableTask(person));
    }

    private List<Assignable> filterAssignableTask(Person assignee){
        return taskManagementSystemRepository.getTasksWithAssignee()
                .stream()
                .filter(story -> story.getAssignee() != null)
                .filter(task -> task.getAssignee().equals(assignee))
                .collect(Collectors.toList());
    }
}
