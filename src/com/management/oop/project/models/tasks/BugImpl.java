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
                   BugSeverityEnum bugSeverityEnum, BugStatusEnum status) {
        super(id, title, description);
        this.steps = steps;
        this.priorityEnum = priorityEnum;
        this.bugSeverityEnum = bugSeverityEnum;
        this.status = BugStatusEnum.ACTIVE;
        this.assignee = assignee;
        addHistory(new EventLogImpl("Bug was created."));
    }

    public void setStatus(BugStatusEnum status) {
        this.status = status;
    }
    @Override
    public void changePriorityEnum(PriorityEnum priorityEnum) {
        this.priorityEnum = priorityEnum;
    }
    @Override
    public void changeSeverityEnum(BugSeverityEnum bugSeverityEnum){
        this.bugSeverityEnum=bugSeverityEnum;
    }
    @Override
    public void advanceStatus(){
        switch (getStatus()){
            case ACTIVE -> setStatus(BugStatusEnum.FIXED);
        }
    }
    @Override
    public void revertStatus(){
        switch (getStatus()){
            case FIXED -> setStatus(BugStatusEnum.ACTIVE);
        }
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
