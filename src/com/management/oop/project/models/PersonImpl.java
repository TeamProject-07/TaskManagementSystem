package com.management.oop.project.models;

import com.management.oop.project.models.contracts.EventLog;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.contracts.Task;

import java.util.ArrayList;
import java.util.List;

public class PersonImpl implements Person {
    private String name;
    private List<Task> tasks;
    private List<EventLog> histories;

    public PersonImpl(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
        this.histories = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public List<EventLog> getHistory() {
        return new ArrayList<>(histories);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getName() {
        return null;
    }

}
