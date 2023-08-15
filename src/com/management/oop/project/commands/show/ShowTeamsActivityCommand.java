package com.management.oop.project.commands.show;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Board;
import com.management.oop.project.models.contracts.EventLog;
import com.management.oop.project.models.contracts.Team;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;

public class ShowTeamsActivityCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
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
        Team team=taskManagementSystemRepository.findTeamByName(teamName);
        List<EventLog> histories = team.getHistory();
        StringBuilder result = new StringBuilder();
        result.append(String.format("Show %s activity:", teamName)).append(System.lineSeparator());
        if (histories.size() == 0) {
            throw new IllegalArgumentException("Don't have activity.");
        }
        for (int i = 0; i < histories.size(); i++) {
            result.append(String.format("%s ", histories.get(i)));
        }
        return result.toString();
    }
}
