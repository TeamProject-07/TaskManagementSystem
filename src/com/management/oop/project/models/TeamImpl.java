package com.management.oop.project.models;

import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Board;
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
    private List<Person> people;
    private List<Board> boards;

    public TeamImpl(String name, List<Person> people, List<Board> boards) {
        setName(name);
        this.people = people;
        this.boards = boards;
    }

    private void setName(String name) {
        ValidationHelpers.validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH, NAME_LENGTH_ERROR);
        this.name = name;
    }

    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }

    public List<Person> getPeople() {
        return new ArrayList<>(people);
    }

    void addPerson(Person person) {
        people.add(person);
    }

    @Override
    public Person getPerson() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getId() {
        return 0;
    }
}
