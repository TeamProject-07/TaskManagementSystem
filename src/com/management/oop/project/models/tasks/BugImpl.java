package com.management.oop.project.models.tasks;

import com.management.oop.project.models.EventLogImpl;
import com.management.oop.project.models.PersonImpl;
import com.management.oop.project.models.contracts.Assignable;
import com.management.oop.project.models.contracts.Bug;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.enums.BugSeverityEnum;
import com.management.oop.project.models.enums.BugStatusEnum;
import com.management.oop.project.models.enums.PriorityEnum;

import java.util.List;

public class BugImpl extends TaskBase implements Bug, Assignable {
    public static final String BUG_CREATED = "Bug with ID:%d was created.";
    public static final String STATUS_WAS_CHANGED = "Status was changed.";
    public static final String PRIORITY_WAS_CHANGED = "Priority was changed.";
    public static final String SEVERITY_WAS_CHANGED = "Severity was changed.";
    private List<String> steps;
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
        addHistory(new EventLogImpl(String.format(BUG_CREATED, getId())));
    }

    @Override
    public void changeStatus(BugStatusEnum status) {
        this.status = status;
        addHistory(new EventLogImpl(STATUS_WAS_CHANGED));
    }
    @Override
    public void changePriorityEnum(PriorityEnum priorityEnum) {
        this.priorityEnum = priorityEnum;
        addHistory(new EventLogImpl(PRIORITY_WAS_CHANGED));

    }

    @Override
    public void changeSeverityEnum(BugSeverityEnum bugSeverityEnum){
        this.bugSeverityEnum=bugSeverityEnum;
        addHistory(new EventLogImpl(SEVERITY_WAS_CHANGED));
    }

    @Override
    public Person getAssignee() {
        return assignee;
    }

    @Override
    public void assignTask(Person assignee) {
        this.assignee=assignee;
    }
    @Override
    public void unAssignTask(Person assignee) {
        this.assignee = null;
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

    @Override
    public String getAsString() {
        return String.format("""
                %s
                Priority: %s
                Severity: %s
                Status: %s""",
                super.getAsString(),
                getBugPriorityEnum(),
                getBugSeverityEnum(),
                getStatus());
    }

}
