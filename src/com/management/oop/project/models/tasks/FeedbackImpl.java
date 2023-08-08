package com.management.oop.project.models.tasks;

import com.management.oop.project.models.contracts.Feedback;
import com.management.oop.project.models.enums.FeedbackStatusEnum;

public class FeedbackImpl extends TaskBase implements Feedback {

    public static final int MIN_RATING = 0;

    public static final int MAX_RATING = 100;

    public static final String RATING_ERROR_MESSAGE = "Rating needs to be between 0 and 100";

    private int rating;

    private FeedbackStatusEnum status;

    public FeedbackImpl(String title, String description, int rating){
        super(title, description);
    }




    //@Override
   // public int getId() {
   //     return id;
   // }

    // da napishem tozi metod v klassa, shtoto nqma smysmul da go over ride ame?
  // @Override
  // public String getTitle() {
  //     return tit;
  // }

 // @Override
 // public String getDescription() {
 //     return null;
 // }

 // @Override
 // public void addComment() {

 // }

 // @Override
 // public void removeComment() {

 // }
}
