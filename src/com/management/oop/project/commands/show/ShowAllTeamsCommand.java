package com.management.oop.project.commands.show;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.BoardImpl;
import com.management.oop.project.models.contracts.Board;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.contracts.Team;
import com.management.oop.project.utils.ListingHelpers;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;

public class ShowAllTeamsCommand implements Command {

    private final List<Team>teams;
    public ShowAllTeamsCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
    this.teams=taskManagementSystemRepository.getTeams();
    }

    @Override
    public String execute(List<String> parameters) {
        if (teams.isEmpty()) {
            return "There are no registered tickets.";
        }
        return ListingHelpers.getAsString(teams);
    }
}
