package com.management.oop.project.models.contracts;

import com.management.oop.project.models.enums.FeedbackStatusEnum;

public interface Feedback extends Task {
    void changeStatus(FeedbackStatusEnum status);

    void changeRating(int rating);

    FeedbackStatusEnum getStatus();

    int getRating();


}
