package com.management.oop.project.models.tasks;

import com.management.oop.project.models.EventLogImpl;
import com.management.oop.project.models.contracts.Feedback;
import com.management.oop.project.models.enums.FeedbackStatusEnum;
import com.management.oop.project.utils.ValidationHelpers;

public class FeedbackImpl extends TaskBase implements Feedback {

    public static final int MIN_RATING = 0;

    public static final int MAX_RATING = 100;

    public static final String RATING_ERROR_MESSAGE = "Rating needs to be between 0 and 100";

    private int rating;

    private FeedbackStatusEnum status;

    public FeedbackImpl(int id, String title, String description, int rating, FeedbackStatusEnum status) {
        super(id, title, description);
        setRating(rating);
        this.status = status;
        addHistory(new EventLogImpl("Feedback was created."));
    }

    public int getRating() {
        return rating;
    }


    private void setRating(int rating) {
        ValidationHelpers.validateValueInRange(rating, MIN_RATING, MAX_RATING, RATING_ERROR_MESSAGE);
        this.rating = rating;
    }

    @Override
    public void changeRating(int rating) {
        ValidationHelpers.validateValueInRange(rating, MIN_RATING, MAX_RATING, RATING_ERROR_MESSAGE);
        this.rating = rating;
        addHistory(new EventLogImpl("Rating was changed."));
    }

    public FeedbackStatusEnum getStatus() {
        return status;
    }

    @Override
    public void changeStatus(FeedbackStatusEnum feedbackStatusEnum) {
        this.status = feedbackStatusEnum;
        addHistory(new EventLogImpl("Status was changed"));
    }

}
