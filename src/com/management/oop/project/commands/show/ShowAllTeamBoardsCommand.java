package com.management.oop.project.commands.show;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Team;
import com.management.oop.project.utils.ListingHelpers;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;

public class ShowAllTeamBoardsCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public ShowAllTeamBoardsCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);
        return getBoardAsString(teamName);
    }
    private String getBoardAsString(String teamName) {
        Team team = taskManagementSystemRepository.findTeamByName(teamName);
        return ListingHelpers.boardsToString(team.getBoards());
    }
}
