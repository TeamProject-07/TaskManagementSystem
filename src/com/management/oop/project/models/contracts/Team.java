package com.management.oop.project.models.contracts;

import com.management.oop.project.models.PersonImpl;

public interface Team extends Task, History{

    Person getPerson();
}
