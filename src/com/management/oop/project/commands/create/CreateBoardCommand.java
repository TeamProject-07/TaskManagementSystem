package com.management.oop.project.commands.create;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;

public class CreateBoardCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String BOARD_CREATED = "Board with name %s was created.";
    public static final String BOARD_EXISTS_ERROR = "Board with name %s already exists";
    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public CreateBoardCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String boardName= parameters.get(0);
        String teamName= parameters.get(1);
        return createBoard(boardName, teamName);
    }
    private  String createBoard(String boardName, String teamName) {
        if (taskManagementSystemRepository.boardExist(boardName)){
            throw new IllegalArgumentException(String.format(BOARD_EXISTS_ERROR, boardName));
        }

        taskManagementSystemRepository.createBoard(boardName, teamName);
        return String.format(BOARD_CREATED, boardName);
    }
}
