package com.management.oop.project.models.contracts;

import java.util.List;

public interface Task extends Commentable, Identifiable{


     String getTitle();

     String getDescription();

     // PriorityType getPriority();

    //Status getStatus();

    //getAssignee();

    void addComment();

    void removeComment();

    //List<String> getHistory();





}
