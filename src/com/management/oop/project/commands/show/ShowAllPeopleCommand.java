package com.management.oop.project.commands.show;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.utils.ListingHelpers;

import java.util.List;

public class ShowAllPeopleCommand implements Command {
    private final List<Person> people;

    public ShowAllPeopleCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        people = taskManagementSystemRepository.getPeople();
    }

    @Override
    public String execute(List<String> parameters) {
        if (people.isEmpty()) {
            return "There are no registered people.";
        }
        return ListingHelpers.getAsString(people);
    }
}
