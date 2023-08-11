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
        addHistory(new EventLogImpl("Person was created."));
    }
    protected void addHistory(EventLog eventLog){
        histories.add(eventLog);
    }
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }


    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<EventLog> getHistory() {
        return new ArrayList<>(histories);
    }

    @Override
    public void assignTask(Task task) {
        this.tasks.add(task);
        addHistory(new EventLogImpl("Task was assigned to person."));
    }

    @Override
    public void unAssignTask(Task task) {
        this.tasks.remove(task);
        addHistory(new EventLogImpl("Task was unassigned to person."));
    }
    @Override
    public String getAsString() {
        return String.format("""
                Person: %s""", getName());
    }
}
