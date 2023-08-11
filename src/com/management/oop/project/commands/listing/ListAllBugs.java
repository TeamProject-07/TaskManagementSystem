package com.management.oop.project.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Board;
import com.management.oop.project.models.contracts.Bug;
import com.management.oop.project.models.contracts.Task;
import com.management.oop.project.models.contracts.Team;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.Comparator;
import java.util.List;

public class ListAllBugs implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    TaskManagementSystemRepository taskManagementSystemRepository;
    private List<Bug> bugs;

    public ListAllBugs(TaskManagementSystemRepository taskManagementSystemRepository, List<Bug> bugs) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
        this.bugs= taskManagementSystemRepository.getAllBugs();
    }


    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String typeOfListing= parameters.get(0);
        switch (typeOfListing){
            case "list":
                return list();
            case "filter":
                return filter();
            case "sort":
                return sort();
            default:
                return String.format("You should choose correct type of listing.(filter/sort)");
        }
    }

    private String list() {
        StringBuilder list= new StringBuilder();
        for (Bug bug : bugs) {
            list
                    .append(bug.getId()).append(" ")
                    .append(bug.getTitle()).append(" ")
                    .append(bug.getDescription())
                    .append(System.lineSeparator());
        }
        return list.toString();
    }

    private String filter() {
        StringBuilder list=new StringBuilder();
        for (Bug bug: bugs) {
            list.append(bug.getStatus()).append(System.lineSeparator());
        }
        return list.toString();
    }

    private String sort() {
        StringBuilder list=new StringBuilder();
        bugs.sort(Comparator.comparing(Task::getTitle));
        for (Bug bug : bugs) {
            list.append(bug.getTitle()).append(System.lineSeparator());
        }
        return list.toString();

    }







}
