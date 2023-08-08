package com.management.oop.project.models.tasks;

import com.management.oop.project.models.contracts.Bug;

import java.util.List;

public class BugImpl extends TaskBase implements Bug {
    private List<String>steps;

    public BugImpl(String title, String description) {
        super(title, description);
    }

    @Override
    public void addComment() {

    }

    @Override
    public void removeComment() {

    }
}
