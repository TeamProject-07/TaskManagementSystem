package com.management.oop.project.models;

import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.contracts.Task;

import java.util.ArrayList;
import java.util.List;

public class PersonImpl implements Person {
    private List<Task> tasks = new ArrayList<>();

    @Override
    public String toString() {
        return super.toString();
    }
}
