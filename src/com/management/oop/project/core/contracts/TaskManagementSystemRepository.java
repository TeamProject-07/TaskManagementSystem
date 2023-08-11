package com.management.oop.project.core.contracts;

import com.management.oop.project.models.contracts.*;
import com.management.oop.project.models.enums.*;

import java.util.List;

public interface TaskManagementSystemRepository {
    Board createBoard(String boardName, String teamName);

    List<Team> getTeams();

    List<Person> getPeople();
    List<Task> getAllTasks();

    List<Bug> getAllBugs();
    List<Feedback> getAllFeedback();

    Team findTeamByName(String teamName);


    Person findPersonByName(String personName);

    Board findBoardByName(String boardName);
    Bug findBugById(int id);
    Story findStoryById(int id);
    Feedback findFeedbackById(int id);
    Task findTaskById(int id);

    Person createPerson(String name);

    boolean personExist(String name);

    boolean personHasTeam(String personName);


    void addPersonToTeam(Person person, Team team);

    boolean boardExist(String boardName);
    boolean taskExist(int id);

    Bug createBug(String boardName, String title, String description, List<String> steps,
                  PriorityEnum priorityEnum, BugSeverityEnum bugSeverityEnum);

    Story createStory(String boardName, String title, String description, PriorityEnum priorityEnum,
                      StorySizeEnum storySizeEnum, StoryStatusEnum storyStatusEnum);

    Feedback createFeedback(String boardName, String title, String description, int rating,
                            FeedbackStatusEnum feedbackStatusEnum);

    boolean teamExist(String teamName);

    Team createTeam(String teamName);
    void addComment(String message, Person author);



//    Bug changePriorityEnum(PriorityEnum priorityEnum);
//    Bug changeSeverityEnum(BugSeverityEnum bugSeverityEnum);
//    Bug advanceStatus();
//    Bug revertStatus();
}




