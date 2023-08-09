package com.management.oop.project.models.contracts;

import java.util.List;

public interface Task extends Commentable, Identifiable{


     String getTitle();

     String getDescription();

     // PriorityType getPriority();

    //Status getStatus();

    //getAssignee();

    void addComment(Comment comment);

    //List<String> getHistory();





}
