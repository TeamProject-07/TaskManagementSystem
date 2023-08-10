package com.management.oop.project.models.contracts;

import com.management.oop.project.models.enums.BugSeverityEnum;
import com.management.oop.project.models.enums.PriorityEnum;

public interface Bug extends Task {
    void revertStatus();

    void advanceStatus();

    void changePriorityEnum(PriorityEnum priorityEnum);

    void changeSeverityEnum(BugSeverityEnum bugSeverityEnum);

}
