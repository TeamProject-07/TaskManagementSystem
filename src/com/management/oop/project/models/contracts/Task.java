package com.management.oop.project.models.contracts;

import java.util.List;

public interface Task {
     int getId();

     String getTitle();

     String getDescription();

     // PriorityType getPriority();

    //Status getStatus();

    //getAssignee();

    void addComment();

    void removeComment();

    //List<String> getHistory();





}
