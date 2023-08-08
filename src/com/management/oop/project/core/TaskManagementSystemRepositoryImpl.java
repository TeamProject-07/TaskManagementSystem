package com.management.oop.project.core;

import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.contracts.Task;
import com.management.oop.project.models.contracts.Team;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementSystemRepositoryImpl implements TaskManagementSystemRepository {
    public static final String PERSON_NOT_FOUND = "Person not found";
    public static final String TEAM_NOT_FOUND = "Team not found.";
    public static final String PERSON_HAS_TEAM_ERROR = "Person with name %s already have team.";
    private int nextId;
    private final List<Team> teams = new ArrayList<>();
    private final List<Person> people = new ArrayList<>();
    private final List<Task> tasks = new ArrayList<>();

    public TaskManagementSystemRepositoryImpl() {
        nextId = 0;
    }

    @Override
    public boolean personExist(String personName) {
        boolean exists= false;
        for (Person person : people) {
            if (person.getName().equalsIgnoreCase(personName)){
                exists=true;
                break;
            }
        }
        return exists;
    }

    @Override
    public boolean personHasTeam(String personName) {
        boolean exists = false;
        for (Team team: teams){
            if (team.getPerson().getName().equalsIgnoreCase(personName)){
                exists = true;
                break;
            }
        }
        return exists;
    }

    @Override
    public Person findPersonByName(String personName) {
        for (Person person : people){
            if (person.getName().equalsIgnoreCase(personName)){
                return person;
            }
        }
        throw new IllegalArgumentException(PERSON_NOT_FOUND);
    }

    @Override
    public Team findTeamByName(String teamName) {
        for (Team team : teams) {
            if (team.getName().equalsIgnoreCase(teamName)){
                return team;
            }
        }
        throw new IllegalArgumentException(TEAM_NOT_FOUND);
    }
    public boolean ifPersonIsInTeam(Person person, Team team) {
            if (team.getPerson().getName().contains(person.getName())){
                throw new IllegalArgumentException(String.format(PERSON_HAS_TEAM_ERROR, person.getName()));
        }
        return false;
    }

    @Override
    public boolean teamExist(String teamName) {
        boolean exists = false;

        for (Team team: teams){
            if (team.getName().equalsIgnoreCase(teamName)){
                exists = true;
                break;
            }
        }
        return exists;
    }




//    @Override
//    public void addPersonToTeam(Person person, Team team) {
//        Person person= findPersonByName(personName);
//    }
}
