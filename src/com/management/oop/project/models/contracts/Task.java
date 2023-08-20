package com.management.oop.project.models.contracts;

import java.util.List;

public interface Task extends Commentable, Identifiable, Printable{


     String getTitle();

     String getDescription();

    void addComment(Comment comment);






}
