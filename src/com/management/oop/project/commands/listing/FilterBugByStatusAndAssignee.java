package com.management.oop.project.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Assignable;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.enums.BugStatusEnum;
import com.management.oop.project.utils.ListingHelpers;
import com.management.oop.project.utils.ParsingHelpers;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;
import java.util.stream.Collectors;

public class FilterBugByStatusAndAssignee implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public FilterBugByStatusAndAssignee(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        BugStatusEnum statusEnum = ParsingHelpers.tryParseEnum(parameters.get(1), BugStatusEnum.class);
        String personName = parameters.get(0);
        Person person = taskManagementSystemRepository.findPersonByName(personName);

        return filterBugByAssigneeAndStatus(person, statusEnum);
    }



    private String filterBugByAssigneeAndStatus(Person assignee, BugStatusEnum statusEnum) {
        return ListingHelpers.getAsString(taskManagementSystemRepository.getAllBugs()
                .stream()
                .filter(bug -> bug.getAssignee() != null)
                .filter(bug -> bug.getAssignee().getName().equals(assignee.getName()))
                .filter(bug -> bug.getStatus().equals(statusEnum))
                .toList());
    }

}
