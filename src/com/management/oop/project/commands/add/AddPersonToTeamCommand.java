package com.management.oop.project.commands.add;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.contracts.Team;

import java.util.List;

public class AddPersonToTeamCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String PERSON_ADDED_TO_TEAM = "Person % was added to team";
    public static final String PERSON_ALREADY_ADDED_ERROR = "Person already is in this team.";
    public static final String PERSON_ADDED = "Person added to team.";
    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public AddPersonToTeamCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;

    }

    @Override
    public String execute(List<String> parameters) {
        return null;
    }


    private String addPersonToTeam(String personName, String teamName) {
        if (taskManagementSystemRepository.personExist(personName)) {
            Person person = taskManagementSystemRepository.findPersonByName(personName);
            Team team = taskManagementSystemRepository.findTeamByName(teamName);

            if (!taskManagementSystemRepository.ifPersonIsInTeam(person, team)) {
                taskManagementSystemRepository.addPersonToTeam(person, team);
                return String.format(PERSON_ADDED);

            }
        }
        throw new IllegalArgumentException(PERSON_ALREADY_ADDED_ERROR);
    }
}
