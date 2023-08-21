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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person assignee = (PersonImpl) o;
        return name.equals(assignee.getName());
    }
}
