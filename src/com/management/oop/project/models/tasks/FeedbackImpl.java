package com.management.oop.project.models.tasks;

import com.management.oop.project.models.EventLogImpl;
import com.management.oop.project.models.contracts.Feedback;
import com.management.oop.project.models.enums.FeedbackStatusEnum;
import com.management.oop.project.utils.ValidationHelpers;

public class FeedbackImpl extends TaskBase implements Feedback {

    public static final int MIN_RATING = 0;

    public static final int MAX_RATING = 100;

    public static final String RATING_ERROR_MESSAGE = "Rating needs to be between 0 and 100";
    public static final String STATUS_WAS_CHANGED = "Status was changed";
    public static final String RATING_WAS_CHANGED = "Rating was changed.";
    public static final String FEEDBACK_CREATED = "Feedback with ID:%d was created.";

    private int rating;

    private FeedbackStatusEnum status;

    public FeedbackImpl(int id, String title, String description, int rating, FeedbackStatusEnum status) {
        super(id, title, description);
        setRating(rating);
        this.status = status;
        addHistory(new EventLogImpl(String.format(FEEDBACK_CREATED, getId())));
    }

    public int getRating() {
        return rating;
    }


    private void setRating(int rating) {
        ValidationHelpers.validateValueInRange(rating,
                MIN_RATING,
                MAX_RATING,
                RATING_ERROR_MESSAGE);
        this.rating = rating;
    }

    @Override
    public void changeRating(int rating) {
        ValidationHelpers.validateValueInRange(rating,
                MIN_RATING, MAX_RATING,
                RATING_ERROR_MESSAGE);
        this.rating = rating;
        addHistory(new EventLogImpl(RATING_WAS_CHANGED));
    }

    public FeedbackStatusEnum getStatus() {
        return status;
    }

    @Override
    public void changeStatus(FeedbackStatusEnum feedbackStatusEnum) {
        this.status = feedbackStatusEnum;
        addHistory(new EventLogImpl(STATUS_WAS_CHANGED));
    }

    @Override
    public String getAsString() {
        return String.format("""
                %s
                Rating: %d
                Status: %s""",
                super.getAsString(),
                getRating(),
                getStatus());
    }
}
