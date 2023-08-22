package com.management.oop.project.models.contracts;

public interface Assignable extends Task {
    void assignTask(Person assignee);

    void unAssignTask(Person assignee);

    int getId();

    Person getAssignee();
}
