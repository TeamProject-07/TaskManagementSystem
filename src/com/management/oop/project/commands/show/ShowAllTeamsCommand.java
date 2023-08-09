package com.management.oop.project.commands.show;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.contracts.Team;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;

public class ShowAllTeamsCommand implements Command {

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public ShowAllTeamsCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        List<Team> teams = taskManagementSystemRepository.getTeams();
        StringBuilder result = new StringBuilder();
        if (teams.size() == 0) {
            throw new IllegalArgumentException("No teams have been created");
        }
        for (int i = 0; i < teams.size(); i++) {
            result.append(String.format("%s, ", teams.get(i).toString()));
        }

        return result.toString();
    }



}
