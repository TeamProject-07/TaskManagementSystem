package com.management.oop.project.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Bug;
import com.management.oop.project.models.enums.BugStatusEnum;
import com.management.oop.project.utils.ListingHelpers;
import com.management.oop.project.utils.ParsingHelpers;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListAllBugs implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    TaskManagementSystemRepository taskManagementSystemRepository;
    private List<Bug> bugs;

    public ListAllBugs(TaskManagementSystemRepository taskManagementSystemRepository, List<Bug> bugs) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
        this.bugs = taskManagementSystemRepository.getAllBugs();
    }


    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String typeOfListing = parameters.get(0);
        String parameter = parameters.get(1);
        switch (typeOfListing) {
            case "list":
                return list();
            case "filter":
                return filterBug(parameter);
            case "sort":
                return sort();
            default:
                return String.format("You should choose correct type of listing.(filter/sort)");
        }
    }

    private String list() {
        StringBuilder list = new StringBuilder();
        for (Bug bug : bugs) {
            list
                    .append(bug.getId()).append(" ")
                    .append(bug.getTitle()).append(" ")
                    .append(bug.getDescription())
                    .append(System.lineSeparator());
        }
        return list.toString();
    }

    private String filterBug(String status) {
        BugStatusEnum statusEnum = ParsingHelpers.tryParseEnum(status, BugStatusEnum.class);
        bugs
                .stream()
                .filter(bug -> bug.getStatus().equals(status))
                .collect(Collectors.toList());
        return bugs.toString();
    }

    private String sort() {
        StringBuilder list = new StringBuilder();
        bugs.sort(Comparator.comparing(Bug::getTitle).
                thenComparing(Bug::getBugPriorityEnum).
                thenComparing(Bug::getBugSeverityEnum));
        for (Bug bug : bugs) {
            list.append(bug.getTitle()).append(" ")
                    .append(bug.getBugPriorityEnum()).append(" ")
                    .append(bug.getBugSeverityEnum())
                    .append(System.lineSeparator());
        }
        return list.toString();
    }
}
