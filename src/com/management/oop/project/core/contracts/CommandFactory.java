package com.management.oop.project.core.contracts;

import com.management.oop.project.commands.contracts.Command;

public interface CommandFactory {
    Command createCommandFromCommandName(String commandName,
                                         TaskManagementSystemRepository taskManagementSystemRepository);

}
