package com.management.oop.project.models;

import com.management.oop.project.models.contracts.EventLog;
import com.management.oop.project.models.contracts.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonImpl implements Person {
    public static final String PERSON_WAS_CREATED = "Person was created.";
    private String name;
    private List<EventLog> histories;

    public PersonImpl(String name) {
        this.name = name;
        this.histories = new ArrayList<>();
        addHistory(new EventLogImpl(PERSON_WAS_CREATED));
    }

    protected void addHistory(EventLog eventLog) {
        histories.add(eventLog);
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
