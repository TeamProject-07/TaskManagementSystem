package com.management.oop.project.commands.show;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Board;
import com.management.oop.project.models.contracts.EventLog;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;

public class ShowBoardActivityCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String SHOW_BOARD_ACTIVITY = "Show %s activity:";
    public static final String DO_NOT_HAVE_ACTIVITY = "Don't have activity.";
    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public ShowBoardActivityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }


    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String boardName = parameters.get(0);
        return getBoardActivity(boardName);
    }

    private String getBoardActivity(String boardName) {
        Board board = taskManagementSystemRepository.findBoardByName(boardName);
        List<EventLog> histories = board.getHistory();
        StringBuilder result = new StringBuilder();
        result.append(String.format(SHOW_BOARD_ACTIVITY, boardName)).append(System.lineSeparator());
        if (histories.size() == 0) {
            throw new IllegalArgumentException(DO_NOT_HAVE_ACTIVITY);
        }
        for (EventLog history : histories) {
            result.append(String.format("%s ", history)).append(System.lineSeparator());
        }
        return result.toString();
    }
}
