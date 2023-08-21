package com.management.oop.project.models;

import com.management.oop.project.models.contracts.EventLog;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.contracts.Task;

import java.util.ArrayList;
import java.util.List;

public class PersonImpl implements Person {
    private String name;
    private List<EventLog> histories;

    public PersonImpl(String name) {
        this.name = name;
        this.histories = new ArrayList<>();
        addHistory(new EventLogImpl("Person was created."));
    }
    protected void addHistory(EventLog eventLog){
        histories.add(eventLog);
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
    public String getAsString() {
        return String.format("""
                Person: %s""", getName());
    }
}
