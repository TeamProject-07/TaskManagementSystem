package com.management.oop.project.models;

import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.contracts.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamImpl implements Team {
    private List<Person>people;

    public List<Person> getPeople() {
        return new ArrayList<>(people);
    }
    void addPerson(Person person){
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
