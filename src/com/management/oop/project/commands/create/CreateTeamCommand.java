package com.management.oop.project.commands.create;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.contracts.Team;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.Arrays;
import java.util.List;

public class CreateTeamCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    public static final String TEAM_CREATED = "Team with name %s was created.";
    public static final String TEAM_EXISTS_ERROR = "Team with name %s already exists";
    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public CreateTeamCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);
        return createTeam(teamName);
    }

    private String createTeam(String teamName) {
        if (taskManagementSystemRepository.teamExist(teamName)) {
            throw new IllegalArgumentException(String.format(TEAM_EXISTS_ERROR, teamName));
        }
        taskManagementSystemRepository.createTeam(teamName);
        return String.format(TEAM_CREATED, teamName);
    }

    private String getMemberAsString(String teamName) {
        Team team = taskManagementSystemRepository.findTeamByName(teamName);
        return String.valueOf(team.getPeople());
    }
}
