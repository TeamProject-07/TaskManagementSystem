package com.management.oop.project.models.contracts;

public interface Team extends History{


    //I think getPerson and getId dont have in this intereface
    Person getPerson();
    String getName();
    int getId();
}
