package com.management.oop.project.models;

import com.management.oop.project.models.contracts.Comment;

public class CommentImpl implements Comment {

    private String message;

    private String author;

    public CommentImpl(String message, String author) {
        setMessage(message);
        setAuthor(author);
    }

    @Override
    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    private void setAuthor(String author) {
        this.author = author;
    }

    public String toString() {
        return String.format("Note: %s\n" + "By: %s\n",
                message, author);
    }
}
