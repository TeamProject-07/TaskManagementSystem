package com.management.oop.project.models.contracts;

import com.management.oop.project.models.enums.BugSeverityEnum;
import com.management.oop.project.models.enums.BugStatusEnum;
import com.management.oop.project.models.enums.FeedbackStatusEnum;
import com.management.oop.project.models.enums.PriorityEnum;

public interface Feedback extends Task{
    void changeStatus(FeedbackStatusEnum status);
    void changeRating(int rating);
    FeedbackStatusEnum getStatus();

    int getRating();



}
