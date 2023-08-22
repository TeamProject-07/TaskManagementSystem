package com.management.oop.project.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Bug;
import com.management.oop.project.models.enums.BugStatusEnum;
import com.management.oop.project.utils.ListingHelpers;
import com.management.oop.project.utils.ParsingHelpers;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;
import java.util.stream.Collectors;

public class FilterBugByStatus implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public FilterBugByStatus(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        BugStatusEnum statusEnum = ParsingHelpers.tryParseEnum(parameters.get(0), BugStatusEnum.class);
        return ListingHelpers.getAsString(filterBug(statusEnum));
    }

    private List<Bug> filterBug(BugStatusEnum statusEnum) {
        return taskManagementSystemRepository.getAllBugs()
                .stream()
                .filter(bug -> bug.getStatus().equals(statusEnum))
                .collect(Collectors.toList());
    }
}
