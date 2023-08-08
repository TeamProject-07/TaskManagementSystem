package com.management.oop.project.models;

import com.management.oop.project.models.contracts.Board;
import com.management.oop.project.models.contracts.History;
import com.management.oop.project.models.contracts.Task;

import java.util.List;

public class BoardImpl implements Board {
    private String name;
    private List<Task>tasks;
    private List<History>histories;

}
