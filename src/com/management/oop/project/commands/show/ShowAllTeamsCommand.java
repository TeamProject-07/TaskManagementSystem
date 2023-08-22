package com.management.oop.project.commands.show;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Team;
import com.management.oop.project.utils.ListingHelpers;

import java.util.List;

public class ShowAllTeamsCommand implements Command {

    public static final String NO_REGISTERED_TEAMS = "There are no registered teams.";
    private final List<Team> teams;

    public ShowAllTeamsCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.teams = taskManagementSystemRepository.getTeams();
    }

    @Override
    public String execute(List<String> parameters) {
        if (teams.isEmpty()) {
            return NO_REGISTERED_TEAMS;
        }
        return ListingHelpers.getAsString(teams);
    }
}
