package com.management.oop.project.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Task;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.Comparator;
import java.util.List;

public class ListAllTasks implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    TaskManagementSystemRepository taskManagementSystemRepository;
    private List<Task> tasks;

    public ListAllTasks(TaskManagementSystemRepository taskManagementSystemRepository, List<Task> tasks) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
        this.tasks = taskManagementSystemRepository.getAllTasks();
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String typeOfListing= parameters.get(0);
        switch (typeOfListing){
            case "list":
                return listAllTasks();
            case "filter":
                return filterByTitle();
            case "sort":
                return sortByTitle();
            default:
                return String.format("You should choose correct type of listing.(filter/sort)");
        }
    }
    private String listAllTasks(){
        StringBuilder list= new StringBuilder();
        for (Task task : tasks) {
            list
                    .append(task.getId()).append(" ")
                    .append(task.getTitle()).append(" ")
                    .append(task.getDescription()).append("")
                    .append(System.lineSeparator());
        }
        return list.toString();
    }
    private String filterByTitle(){
        StringBuilder list=new StringBuilder();
        for (Task task : tasks) {
            list.append(task.getTitle()).append(System.lineSeparator());
        }
        return list.toString();
    }
    private String sortByTitle(){
        StringBuilder list=new StringBuilder();
        tasks.sort(Comparator.comparing(Task::getTitle));
        for (Task task : tasks) {
            list.append(task.getTitle()).append(System.lineSeparator());
        }
        return list.toString();
    }
}
