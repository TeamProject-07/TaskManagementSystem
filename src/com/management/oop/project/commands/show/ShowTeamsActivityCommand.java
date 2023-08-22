package com.management.oop.project.commands.show;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.EventLog;
import com.management.oop.project.models.contracts.Team;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;

public class ShowTeamsActivityCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String SHOW_TEAM_ACTIVITY = "Show %s activity:";
    public static final String DO_NOT_ACTIVITY = "Don't have activity.";
    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public ShowTeamsActivityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }


    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);
        return getTeamActivity(teamName);
    }

    private String getTeamActivity(String teamName) {
        Team team = taskManagementSystemRepository.findTeamByName(teamName);
        List<EventLog> histories = team.getHistory();
        StringBuilder result = new StringBuilder();
        result.append(String.format(SHOW_TEAM_ACTIVITY, teamName)).append(System.lineSeparator());
        if (histories.size() == 0) {
            throw new IllegalArgumentException(DO_NOT_ACTIVITY);
        }
        for (EventLog history : histories) {
            result.append(String.format("%s ", history)).append(System.lineSeparator());
        }
        return result.toString();
    }
}
