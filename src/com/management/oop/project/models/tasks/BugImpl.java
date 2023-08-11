package com.management.oop.project.models.tasks;

import com.management.oop.project.models.EventLogImpl;
import com.management.oop.project.models.contracts.Bug;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.enums.BugSeverityEnum;
import com.management.oop.project.models.enums.BugStatusEnum;
import com.management.oop.project.models.enums.PriorityEnum;

import java.util.ArrayList;
import java.util.List;

public class BugImpl extends TaskBase implements Bug {
    private final List<String> steps;
    private PriorityEnum priorityEnum;
    private BugSeverityEnum bugSeverityEnum;
    private  BugStatusEnum status;
    private Person assignee;

    public BugImpl(int id, String title, String description,
                   List<String> steps, PriorityEnum priorityEnum,
                   BugSeverityEnum bugSeverityEnum) {
        super(id, title, description);
        this.steps = steps;
        this.priorityEnum = priorityEnum;
        this.bugSeverityEnum = bugSeverityEnum;
        this.status = BugStatusEnum.ACTIVE;
        this.assignee = assignee;
        addHistory(new EventLogImpl("Bug was created."));
    }
    @Override
    public void changeStatus(BugStatusEnum status) {
        this.status = status;
        addHistory(new EventLogImpl("Status was changed."));
    }
    @Override
    public void changePriorityEnum(PriorityEnum priorityEnum) {
        this.priorityEnum = priorityEnum;
        addHistory(new EventLogImpl("Priority was changed."));

    }
    @Override
    public void changeSeverityEnum(BugSeverityEnum bugSeverityEnum){
        this.bugSeverityEnum=bugSeverityEnum;
        addHistory(new EventLogImpl("Severity was changed."));
    }


    public PriorityEnum getBugPriorityEnum() {
        return priorityEnum;
    }

    public BugSeverityEnum getBugSeverityEnum() {
        return bugSeverityEnum;
    }

    public BugStatusEnum getStatus() {
        return status;
    }

    public List<String> getSteps() {
        return new ArrayList<>(steps);
    }

    public Person getAssignee() {
        return assignee;
    }


}
