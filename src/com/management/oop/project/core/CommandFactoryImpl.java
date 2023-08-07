package com.management.oop.project.core;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.commands.enums.CommandType;
import com.management.oop.project.core.contracts.CommandFactory;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.utils.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {
    private static final String INVALID_COMMAND = "Invalid command name: %s!";


    @Override
    public Command createCommandFromCommandName(String commandName, TaskManagementSystemRepository taskManagementSystemRepository) {
        CommandType commandType= ParsingHelpers.tryParseEnum(commandName, CommandType.class, String.format(INVALID_COMMAND, commandName));
    switch (commandType){
        case CREATENEWPERSON:
            return null;
        case SHOWALLPEOPLE:
            return null;
        case SHOWPERSONACTIVITY:
            return null;
        case CREATENEWTEAM:
            return null;
        case SHOWALLTEAMS:
            return null;
        case SHOWTEAMSACTIVITY:
            return null;
        case ADDPERSONTOTEAM:
            return null;
        case SHOWALLTEAMMEMBERS:
            return null;
        case CREATENEWBOARDS:
            return null;
        case SHOWALLTEAMBOARDS:
            return null;
        case SHOWBOARDACTIVITY:
            return null;
        case CREATENEWBUGINBOARD:
            return null;
        case CREATENEWSTORYINBOARD:
            return null;
        case CREATENEWFEEDBACKINBOARD:
            return null;
        case CHANGEPRIORITYOFBUG:
            return null;
        case CHANGESEVERITYOFBUG:
            return null;
        case CHANGESTATUSOFBUG:
            return null;
        case CHANGEPRIORITYOFSTORY:
            return null;
        case CHANGESIZEOFSTORY:
            return null;
        case CHANGESTATUSOFSTORY:
            return null;
        case CHANGERATINGOFFEEDBACK:
            return null;
        case CHANGESTATUSOFFEEDBACK:
            return null;
        case ASSIGNTASKTOPERSON:
            return null;
        case UNASSIGNTASKTOPERSON:
            return null;
        default:
            throw new IllegalArgumentException(String.format(INVALID_COMMAND));
    }
    }

}
