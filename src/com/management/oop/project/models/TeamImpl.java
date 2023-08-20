package com.management.oop.project.models;

import com.management.oop.project.models.contracts.Board;
import com.management.oop.project.models.contracts.EventLog;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.contracts.Team;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class TeamImpl implements Team {
    public static final int NAME_MIN_LENGTH = 5;
    public static final int NAME_MAX_LENGTH = 15;
    public static final String NAME_LENGTH_ERROR = "Name should be between 5 and 15 symbols.";
    private String name;
    private final List<Person> people;
    private final List<Board> boards;
    private final List<EventLog>histories;

    public TeamImpl(String name) {
        setName(name);
        this.people = new ArrayList<>();
        this.boards = new ArrayList<>();
        this.histories=new ArrayList<>();
        this.histories.add(new EventLogImpl(String.format("Team with name %s was created.", getName())));
    }

    private void setName(String name) {
        ValidationHelpers.validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH, NAME_LENGTH_ERROR);
        this.name = name;
    }
    @Override
    public void addBoard(Board board){
        boards.add(board);
    }

    @Override
    public List<EventLog> getHistory() {
        return new ArrayList<>(histories);
    }

    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }

    public List<Person> getPeople() {
        return new ArrayList<>(people);
    }

    public void addPerson(Person person) {
        people.add(person);
        addHistory(new EventLogImpl(String.format("Person with name %s was added.", person.getName())));
    }
    protected void addHistory(EventLog eventLog) {
        histories.add(eventLog);
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAsString() {
        return String.format("""
                Team name: %s""", getName());
    }
}
