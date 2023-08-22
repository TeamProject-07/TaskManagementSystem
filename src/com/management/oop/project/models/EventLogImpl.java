package com.management.oop.project.models;

import com.management.oop.project.models.contracts.EventLog;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventLogImpl implements EventLog {
    public static final String DESCRIPTION_CANNOT_BE_EMPTY = "Description cannot be empty";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss");
    private final String description;
    private final LocalDateTime timestamp;

    public EventLogImpl(String description) {
        if (description.isEmpty()) {
            throw new IllegalArgumentException(DESCRIPTION_CANNOT_BE_EMPTY);
        }
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", timestamp.format(formatter), description);
    }
}
