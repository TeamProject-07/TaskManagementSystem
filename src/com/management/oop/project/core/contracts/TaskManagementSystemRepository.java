package com.management.oop.project.core.contracts;

import com.management.oop.project.models.contracts.*;
import com.management.oop.project.models.enums.*;

import java.util.List;

public interface TaskManagementSystemRepository {
    void addPersonToTeam(Person person, Team team);


    List<Team> getTeams();

    List<Person> getPeople();

    List<Task> getAllTasks();

    List<Bug> getAllBugs();

    List<Feedback> getAllFeedback();

    List<Story> getAllStories();

    List<Board> getAllBoards();

    List<Assignable> getAllAssignableTasks();

    Team findTeamByName(String teamName);


    Person findPersonByName(String personName);

    Board findBoardByName(String boardName);

    Bug findBugById(int id);

    Story findStoryById(int id);

    Feedback findFeedbackById(int id);

    Task findTaskById(int id);
    Assignable findAssignableTaskById(int id);


    boolean personHasTeam(String personName);

    boolean personExist(String name);


    boolean boardExist(String boardName);

    boolean taskExist(int id);

    boolean teamExist(String teamName);
    boolean ifTaskIsAssignable(int id);
    boolean ifTaskIsNotAssigned(Assignable task);

    Person createPerson(String name);

    Team createTeam(String teamName);

    Board createBoard(String boardName, String teamName);


    Bug createBug(String boardName, String title, String description, List<String> steps,
                  PriorityEnum priorityEnum, BugSeverityEnum bugSeverityEnum);

    Story createStory(String boardName, String title, String description, PriorityEnum priorityEnum,
                      StorySizeEnum storySizeEnum, StoryStatusEnum storyStatusEnum);

    Feedback createFeedback(String boardName, String title, String description, int rating,
                            FeedbackStatusEnum feedbackStatusEnum);


//    Bug changePriorityEnum(PriorityEnum priorityEnum);
//    Bug changeSeverityEnum(BugSeverityEnum bugSeverityEnum);
//    Bug advanceStatus();
//    Bug revertStatus();
}




