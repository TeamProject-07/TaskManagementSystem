package com.management.oop.project.models;

import com.management.oop.project.models.contracts.Board;
import com.management.oop.project.models.contracts.EventLog;
import com.management.oop.project.models.contracts.Task;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {
    private String name;
    private List<Task>tasks;
    private List<EventLog>histories;

    public BoardImpl(String name) {
        setName(name);
        this.tasks = new ArrayList<>();
        this.histories = new ArrayList<>();
        histories.add(new EventLogImpl("Board was created"));
    }

    @Override
    public List<EventLog> getHistory() {
        return new ArrayList<>(histories);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        ValidationHelpers.validateStringLength(name, 5, 10, "Name should be between 5 and 10 symbols.");
        this.name = name;
    }
}
