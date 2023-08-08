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
    public boolean personExist(String name);


}
