package com.management.oop.project.core.contracts;

import com.management.oop.project.models.contracts.*;
import com.management.oop.project.models.enums.*;

import java.util.List;

public interface TaskManagementSystemRepository {

    List<Team> getTeams();

    List<Person> getPeople();

    List<Task> getTasks();

    Team findTeamByName(String teamName);


    Person findPersonByName(String personName);

    Board findBoardByName(String boardName);
    Task findTaskById(int id);

    Person createPerson(String name);

    boolean personExist(String name);
    boolean taskExist(int id);

    boolean personHasTeam(String personName);


    void addPersonToTeam(Person person, Team team);

    boolean boardExist(String boardName);

    Board createBoard(String boardName);

    Bug createBug(String title, String description, List<String> steps,
                  PriorityEnum priorityEnum, BugSeverityEnum bugSeverityEnum,
                  BugStatusEnum bugStatusEnum);

    Story createStory(String title, String description, PriorityEnum priorityEnum,
                      StorySizeEnum storySizeEnum, StoryStatusEnum storyStatusEnum,
                      Person assignee);

    Feedback createFeedback(String title, String description, int rating,
                            FeedbackStatusEnum feedbackStatusEnum);

    boolean teamExist(String teamName);

    Team createTeam(String teamName);
}




