package com.management.oop.project.core.contracts;

import com.management.oop.project.models.contracts.*;
import com.management.oop.project.models.enums.*;

import java.util.List;

public interface TaskManagementSystemRepository {

    List<Team> getTeams();

    List<Person> getPeople();

    List<Task> getTasks();

    Team findTeamById(int id);

    Person findMemberById(int id);

    Task findTaskById(int id);

    Person createPerson(String name);

    boolean personExist(String name);

    boolean personHasTeam(String personName);

    Person findPersonByName(String personName);

    void addPersonToTeam(Person person, Team team);

    Team findTeamByName(String teamName);

    boolean ifPersonIsInTeam(Person person, Team team);
    boolean boardExist(String boardName);
    Board createBoard(String boardName);

    Bug createBug(String title, String description, List<String> steps,
                  PriorityEnum priorityEnum, SeverityEnum severityEnum,
                  BugStatusEnum bugStatusEnum, String assignee,
                  List<Comment> comments);

    Story createStory(String title, String description, PriorityEnum priorityEnum,
                      StorySizeEnum storySizeEnum, StoryStatusEnum storyStatusEnum,
                      String assignee, List<Comment> comments);

    Feedback createFeedback(String title, String description, int rating,
                            FeedbackStatusEnum feedbackStatusEnum, List<Comment> comments);
}




