package com.management.oop.project.models.contracts;

import com.management.oop.project.models.enums.PriorityEnum;

public interface Bug extends Task{
    void changePriority(PriorityEnum priorityEnum);
}
