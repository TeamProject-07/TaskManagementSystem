package com.management.oop.project.core.contracts;

import com.management.oop.project.models.contracts.*;
import com.management.oop.project.models.enums.*;

import java.util.List;

public interface TaskManagementSystemRepository {

    List<Team> getTeams();

    List<Person> getPeople();

    List<Task> getTasks();

    List<EventLog> getHistory();


    Team findTeamByName(String teamName);

    Team findTeamById(int id);

    Person findPersonByName(String personName);

    Person findMemberById(int id);

    Task findTaskById(int id);

    Person createPerson(String name);

    boolean personExist(String name);

    boolean personHasTeam(String personName);


    void addPersonToTeam(Person person, Team team);

    boolean boardExist(String boardName);

    Board createBoard(String boardName);

    Bug createBug(String title, String description, List<String> steps,
                  PriorityEnum priorityEnum, BugSeverityEnum bugSeverityEnum,
                  BugStatusEnum bugStatusEnum, Person assignee);

    Story createStory(String title, String description, PriorityEnum priorityEnum,
                      StorySizeEnum storySizeEnum, StoryStatusEnum storyStatusEnum,
                      Person assignee);

    Feedback createFeedback(String title, String description, int rating,
                            FeedbackStatusEnum feedbackStatusEnum);

    boolean teamExist(String teamName);

    Team createTeam(String teamName);


}




