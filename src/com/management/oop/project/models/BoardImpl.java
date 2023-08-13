package com.management.oop.project.models;

import com.management.oop.project.models.contracts.*;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {
    public static final int BOARD_NAME_MIN_LENGTH = 5;
    public static final int BOARD_NAME_MAX_LENGTH = 10;
    private String name;
    private final List<Bug> bugs;
    private final List<Story> stories;
    private final List<Feedback> feedbacks;
    private List<EventLog> histories;

    public BoardImpl(String name) {
        setName(name);
        this.bugs = new ArrayList<>();
        this.stories = new ArrayList<>();
        this.feedbacks = new ArrayList<>();
        this.histories = new ArrayList<>();
        histories.add(new EventLogImpl(String.format("Board with name %s was created", getName())));
    }

    @Override
    public void addBug(Bug bug) {
        this.bugs.add(bug);
        histories.add(new EventLogImpl("Bug was created"));
    }

    @Override
    public void addStory(Story story) {
        this.stories.add(story);
        histories.add(new EventLogImpl("Story was created"));
    }

    @Override
    public void addFeedback(Feedback feedback) {
        this.feedbacks.add(feedback);
        histories.add(new EventLogImpl("Feedback was created"));
    }

    @Override
    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.addAll(bugs);
        tasks.addAll(feedbacks);
        tasks.addAll(stories);
        return tasks;
    }

    public List<Bug> getBugs() {
        return new ArrayList<>(bugs);
    }

    public List<Story> getStories() {
        return new ArrayList<>(stories);
    }

    public List<Feedback> getFeedbacks() {
        return new ArrayList<>(feedbacks);
    }

    @Override
    public List<EventLog> getHistory() {
        return new ArrayList<>(histories);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        ValidationHelpers.validateStringLength(name, BOARD_NAME_MIN_LENGTH, BOARD_NAME_MAX_LENGTH,
                "Name should be between 5 and 10 symbols.");
        this.name = name;
    }

    @Override
    public String getAsString() {
        return String.format("""
                Board name: %s""", getName());
    }
}
