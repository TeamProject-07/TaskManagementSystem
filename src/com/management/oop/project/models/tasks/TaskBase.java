package com.management.oop.project.models.tasks;

import com.management.oop.project.models.EventLogImpl;
import com.management.oop.project.models.contracts.Comment;
import com.management.oop.project.models.contracts.EventLog;
import com.management.oop.project.models.contracts.Task;
import com.management.oop.project.models.enums.PriorityEnum;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public abstract class TaskBase implements Task {

    public static final int TITLE_MIN_LENGTH = 10;
    public static final int TITLE_MAX_LENGTH = 50;
    public static final String TITLE_ERROR_MESSAGE = String.format("Title length cannot be less than %d or more than %d symbols long.",
            TITLE_MIN_LENGTH, TITLE_MAX_LENGTH);
    public static final int DESCRIPTION_MIN_LENGTH = 10;
    public static final int DESCRIPTION_MAX_LENGTH = 500;
    public static final String DESCRIPTION_ERROR_MESSAGE = String.format("Description length cannot be less than %d or more than %d symbols long.",
            DESCRIPTION_MIN_LENGTH, DESCRIPTION_MAX_LENGTH);
    private int id;
    private String title;
    private String description;
    private List<Comment> comments;
    private List<EventLog> histories;

    public TaskBase(int id, String title, String description) {
        setId(id);
        setTitle(title);
        setDescription(description);
        this.comments = new ArrayList<>();
        this.histories = new ArrayList<>();
        histories.add(new EventLogImpl("Task was created."));
    }

    private void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        ValidationHelpers.validateStringLength(title, TITLE_MIN_LENGTH, TITLE_MAX_LENGTH, TITLE_ERROR_MESSAGE);
        this.title = title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        ValidationHelpers.validateStringLength(description, DESCRIPTION_MIN_LENGTH, DESCRIPTION_MAX_LENGTH, DESCRIPTION_ERROR_MESSAGE);
        this.description = description;
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
        addHistory(new EventLogImpl("Comment was added."));
    }

    protected void addHistory(EventLog eventLog) {
        histories.add(eventLog);
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    private void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<EventLog> getHistories() {
        return new ArrayList<>(histories);
    }

    private void setHistories(List<EventLog> histories) {
        this.histories = histories;
    }
}
