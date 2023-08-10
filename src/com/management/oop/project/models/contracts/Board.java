package com.management.oop.project.models.contracts;

import java.util.List;

public interface Board {
    String getName();
    List<EventLog>getHistory();
    void addBug(Bug bug);
    void addStory(Story story);
    void addFeedback(Feedback feedback);
    List<Bug> getBugs();
    List<Story> getStories();
    List<Feedback> getFeedbacks();


}
