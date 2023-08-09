package com.management.oop.project.models;

import com.management.oop.project.models.contracts.Comment;
import com.management.oop.project.models.contracts.Commentable;
import com.management.oop.project.models.contracts.Person;

public class CommentImpl implements Comment {

    private String message;

    private Person author;

    public CommentImpl (String message, Person author){
        setMessage(message);
        setAuthor(author);
    }
    @Override
    public String getMessage() {
        return message;
    }

    private void setMessage(String message){
        this.message = message;
    }

    @Override
    public Person getAuthor() {
        return author;
    }

    private void setAuthor(Person author){
        this.author = author;
    }

    public String toString(){
        return String.format("Note: %s\n"+"By: %s\n",message, author);
    }
}
