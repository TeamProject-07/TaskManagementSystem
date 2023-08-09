package com.management.oop.project.commands.show;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Team;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;

public class ShowAllTeamsMembersCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String TEAM_EMPTY = "Team with name %s is empty.";
    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public ShowAllTeamsMembersCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);
        return getMemberAsString(teamName);
    }

    //TODO im not sure this validation is correct

    private String getMemberAsString(String teamName) {

        Team team = taskManagementSystemRepository.findTeamByName(teamName);
        if (team == null) {
            throw new IllegalArgumentException(String.format(TEAM_EMPTY, teamName));
        }
        return team.getPerson().getName();
    }

}
