package com.management.oop.project.core;

import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.*;
import com.management.oop.project.models.enums.*;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementSystemRepositoryImpl implements TaskManagementSystemRepository {
    public static final String PERSON_NOT_FOUND = "Person not found";
    public static final String TEAM_NOT_FOUND = "Team not found.";
    public static final String PERSON_HAS_TEAM_ERROR = "Person with name %s already have team.";
    private int nextId;
    private static final List<Team> teams = new ArrayList<>();
    private final List<Person> people = new ArrayList<>();
    private final List<Task> tasks = new ArrayList<>();
    private final List<Board> boards = new ArrayList<>();


    public TaskManagementSystemRepositoryImpl() {
        nextId = 0;
    }

    @Override
    public boolean personExist(String personName) {
        boolean exists = false;
        for (Person person : people) {
            if (person.getName().equalsIgnoreCase(personName)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    @Override
    public boolean personHasTeam(String personName) {
        boolean exists = false;
        for (Team team : teams) {
            for (Person person : team.getPeople()) {
                if (person.getName().equalsIgnoreCase(personName)) {
                    exists = true;
                    break;
                }
            }
        }
        return exists;
    }

    @Override
    public Person findPersonByName(String personName) {
        for (Person person : people) {
            if (person.getName().equalsIgnoreCase(personName)) {
                return person;
            }
        }
        throw new IllegalArgumentException(PERSON_NOT_FOUND);
    }

    @Override
    public Team findTeamByName(String teamName) {
        for (Team team : teams) {
            if (team.getName().equalsIgnoreCase(teamName)) {
                return team;
            }
        }
        throw new IllegalArgumentException(TEAM_NOT_FOUND);
    }

    @Override
    public boolean boardExist(String boardName) {
        for (Board board : boards) {
            if (board.getName().equalsIgnoreCase(boardName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Person createPerson(String name) {
        return null;
    }

    @Override
    public void addPersonToTeam(Person person, Team team) {

    }

    @Override
    public Board createBoard(String boardName) {
        return null;
    }

    @Override
    public Bug createBug(String title, String description, List<String> steps, PriorityEnum priorityEnum,
                         BugSeverityEnum bugSeverityEnum, BugStatusEnum bugStatusEnum, Person assignee, List<Comment> comments) {
        return null;
    }

    @Override
    public Story createStory(String title, String description, PriorityEnum priorityEnum, StorySizeEnum storySizeEnum,
                             StoryStatusEnum storyStatusEnum, Person assignee, List<Comment> comments) {
        return null;
    }

    @Override
    public Feedback createFeedback(String title, String description, int rating, FeedbackStatusEnum feedbackStatusEnum,
                                   List<Comment> comments) {
        return null;
    }
    //    @Override
//    public void addPersonToTeam(Person person, Team team) {
//        Person person= findPersonByName(personName);
//    }
}
