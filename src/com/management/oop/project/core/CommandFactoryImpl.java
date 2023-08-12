package com.management.oop.project.core;

import com.management.oop.project.commands.add.AddCommentToATask;
import com.management.oop.project.commands.add.AddPersonToTeamCommand;
import com.management.oop.project.commands.assign.AssignTaskToPersonCommand;
import com.management.oop.project.commands.assign.UnassignTaskToPersonCommand;
import com.management.oop.project.commands.change.ChangeBugCommand;
import com.management.oop.project.commands.change.ChangeFeedbackCommand;
import com.management.oop.project.commands.change.ChangeStoryCommand;
import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.commands.create.*;
import com.management.oop.project.commands.enums.CommandType;
import com.management.oop.project.commands.show.*;
import com.management.oop.project.core.contracts.CommandFactory;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.utils.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {
    private static final String INVALID_COMMAND = "Invalid command name: %s!";


    @Override
    public Command createCommandFromCommandName(String commandName, TaskManagementSystemRepository taskManagementSystemRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandName, CommandType.class);
        switch (commandType) {
            case CREATENEWPERSON:
                return new CreatePersonCommand(taskManagementSystemRepository);
            case SHOWALLPEOPLE:
                return new ShowAllPeopleCommand(taskManagementSystemRepository);
            case SHOWPERSONACTIVITY:
                return new ShowPersonActivityCommand(taskManagementSystemRepository);
            case CREATENEWTEAM:
                return new CreateTeamCommand(taskManagementSystemRepository);
            case SHOWALLTEAMS:
                return new ShowAllTeamsCommand(taskManagementSystemRepository);
            case SHOWTEAMSACTIVITY:
                return new ShowTeamsActivityCommand(taskManagementSystemRepository);
            case ADDPERSONTOTEAM:
                return new AddPersonToTeamCommand(taskManagementSystemRepository);
            case SHOWALLTEAMMEMBERS:
                return new ShowAllTeamsMembersCommand(taskManagementSystemRepository);
            case CREATENEWBOARDS:
                return new CreateBoardCommand(taskManagementSystemRepository);
            case SHOWALLTEAMBOARDS:
                return new ShowAllTeamBoardsCommand(taskManagementSystemRepository);
            case SHOWBOARDACTIVITY:
                return new ShowBoardActivityCommand(taskManagementSystemRepository);
            case CREATENEWBUGINBOARD:
                return new CreateNewBug(taskManagementSystemRepository);
            case CREATENEWSTORYINBOARD:
                return new CreateNewStory(taskManagementSystemRepository);
            case CREATENEWFEEDBACKINBOARD:
                return new CreateNewFeedback(taskManagementSystemRepository);
            case CHANGEBUG:
                return new ChangeBugCommand(taskManagementSystemRepository);
            case CHANGESTORY:
                return new ChangeStoryCommand(taskManagementSystemRepository);
            case CHANGEFEEDBACK:
                return new ChangeFeedbackCommand(taskManagementSystemRepository);
            case ASSIGNTASKTOPERSON:
                return new AssignTaskToPersonCommand(taskManagementSystemRepository);
            case UNASSIGNTASKTOPERSON:
                return new UnassignTaskToPersonCommand(taskManagementSystemRepository);
            case ADDCOMMENTTOATASK:
                return new AddCommentToATask(taskManagementSystemRepository);
            default:
                throw new IllegalArgumentException(String.format(INVALID_COMMAND, commandName));
        }
    }
}
