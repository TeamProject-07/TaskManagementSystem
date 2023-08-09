package com.management.oop.project.models;

import com.management.oop.project.models.contracts.EventLog;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventLogImpl implements EventLog {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss");
    private String description;
    private LocalDateTime timestamp;

    public EventLogImpl(String description) {
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }
}
