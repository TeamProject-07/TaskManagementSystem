package com.management.oop.project.models.contracts;

public interface Team extends History{

    Person getPerson();
    String getName();
    int getId();
}
