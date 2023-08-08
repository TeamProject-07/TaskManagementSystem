package com.management.oop.project.core.contracts;

import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.contracts.Task;
import com.management.oop.project.models.contracts.Team;

import java.util.List;

public interface TaskManagementSystemRepository {

    List<Team> getTeams();

    List<Person> getPeople();

    List<Task> getTasks();

    Team findTeamById(int id);

    Person findMemberById(int id);

    Task findTaskById(int id);

    Person createPerson(String name);
   boolean personExist(String name);

    boolean personHasTeam(String personName);

    Person findPersonByName(String personName);

    void addPersonToTeam(String personName, String teamName);
    }



