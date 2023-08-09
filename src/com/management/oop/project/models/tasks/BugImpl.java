package com.management.oop.project.models.tasks;

import com.management.oop.project.models.contracts.Bug;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.enums.BugSeverityEnum;
import com.management.oop.project.models.enums.BugStatusEnum;
import com.management.oop.project.models.enums.PriorityEnum;

import java.util.ArrayList;
import java.util.List;

public class BugImpl extends TaskBase implements Bug {
    private final List<String>steps;
    private final PriorityEnum priorityEnum;
    private final BugSeverityEnum bugSeverityEnum;
    private final BugStatusEnum bugStatusEnum;
    private Person assignee;

    public BugImpl(int id, String title, String description,
                   List<String> steps, PriorityEnum priorityEnum,
                   BugSeverityEnum bugSeverityEnum, BugStatusEnum bugStatusEnum,
                   Person assignee) {
        super(id, title, description);
        this.steps = steps;
        this.priorityEnum = priorityEnum;
        this.bugSeverityEnum = bugSeverityEnum;
        this.bugStatusEnum = bugStatusEnum;
        this.assignee = assignee;
    }

    public PriorityEnum getBugPriorityEnum() {
        return priorityEnum;
    }

    public BugSeverityEnum getBugSeverityEnum() {
        return bugSeverityEnum;
    }

    public BugStatusEnum getBugStatusEnum() {
        return bugStatusEnum;
    }

    public List<String> getSteps() {
        return new ArrayList<>(steps);
    }

    public Person getAssignee() {
        return assignee;
    }
}
