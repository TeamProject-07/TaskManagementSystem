package com.management.oop.project.models.contracts;

public interface Task extends Commentable, Identifiable, Printable {
    String getTitle();

    String getDescription();

    void addComment(Comment comment);
}
