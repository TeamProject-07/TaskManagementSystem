package com.management.oop.project.models;

import com.management.oop.project.models.contracts.EventLog;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventLogImpl implements EventLog {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss");
    private String description;
    private LocalDateTime timestamp;

    public EventLogImpl(String description) {
        if (description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s%n", timestamp.format(formatter), description);
    }
}
