package com.management.oop.project.models.tasks;

import com.management.oop.project.models.contracts.Feedback;
import com.management.oop.project.models.enums.FeedbackStatusEnum;

import java.net.FileNameMap;

public class FeedbackImpl extends TaskBase implements Feedback {

    public static final int MIN_RATING = 0;

    public static final int MAX_RATING = 100;

    public static final String RATING_ERROR_MESSAGE = "Rating needs to be between 0 and 100";

    private int rating;

    private final FeedbackStatusEnum status;

    public FeedbackImpl(int id, String title, String description, int rating, FeedbackStatusEnum status) {
        super(id, title, description);
        setRating(rating);
        this.status = status;
    }

    public int getRating() {
        return rating;
    }

    private void setRating(int rating) {
        this.rating = rating;
    }

    public FeedbackStatusEnum getStatus() {
        return status;
    }

}
