package com.management.oop.project.core;

import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Member;
import com.management.oop.project.models.contracts.Task;
import com.management.oop.project.models.contracts.Team;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementSystemRepositoryImpl implements TaskManagementSystemRepository {
    private int nextId;
    private final List<Team> teams = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();
    private final List<Task> tasks = new ArrayList<>();

    public TaskManagementSystemRepositoryImpl() {
        nextId = 0;
    }


}
