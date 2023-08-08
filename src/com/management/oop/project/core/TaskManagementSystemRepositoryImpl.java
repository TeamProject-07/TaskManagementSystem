package com.management.oop.project.core;

import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.contracts.Task;
import com.management.oop.project.models.contracts.Team;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementSystemRepositoryImpl implements TaskManagementSystemRepository {
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
}
