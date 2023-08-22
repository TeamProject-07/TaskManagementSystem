package com.management.oop.project.models.contracts;

import com.management.oop.project.models.enums.BugSeverityEnum;
import com.management.oop.project.models.enums.BugStatusEnum;
import com.management.oop.project.models.enums.PriorityEnum;

public interface Bug extends Task, Assignable {
    void changeStatus(BugStatusEnum status);

    void changePriorityEnum(PriorityEnum priorityEnum);

    void changeSeverityEnum(BugSeverityEnum bugSeverityEnum);

    BugStatusEnum getStatus();

    PriorityEnum getBugPriorityEnum();

    BugSeverityEnum getBugSeverityEnum();

}
