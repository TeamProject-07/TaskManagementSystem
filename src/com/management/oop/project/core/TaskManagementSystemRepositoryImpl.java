package com.management.oop.project.core;

import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.BoardImpl;
import com.management.oop.project.models.PersonImpl;
import com.management.oop.project.models.TeamImpl;
import com.management.oop.project.models.contracts.*;
import com.management.oop.project.models.enums.*;
import com.management.oop.project.models.tasks.BugImpl;
import com.management.oop.project.models.tasks.FeedbackImpl;
import com.management.oop.project.models.tasks.StoryImpl;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementSystemRepositoryImpl implements TaskManagementSystemRepository {
    public static final String PERSON_NOT_FOUND = "Person not found";
    public static final String TEAM_NOT_FOUND = "Team not found.";
    private int nextId;
    private final List<Team> teams;
    private final List<Person> people;
    private final List<Task> tasks;
    private final List<Board> boards;


    public TaskManagementSystemRepositoryImpl() {
        this.teams = new ArrayList<>();
        this.people = new ArrayList<>();
        this.tasks = new ArrayList<>();
        this.boards = new ArrayList<>();
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
    public Task findTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        throw new IllegalArgumentException("There is no task with this ID.");
    }
    public Board findBoardByName(String boardName){
        for (Board board : boards) {
            if (board.getName().equalsIgnoreCase(boardName)){
                return board;
            }
        }
        throw new IllegalArgumentException("There is no board with this name.");
    }

    @Override
    public List<Team> getTeams() {
        return new ArrayList<>(teams);
    }

    @Override
    public List<Person> getPeople() {
        return new ArrayList<>(people);
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
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
    public boolean teamExist(String teamName) {
        for (Team team : teams) {
            if (team.getName().equalsIgnoreCase(teamName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean taskExist(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Person createPerson(String name) {
        Person person = new PersonImpl(name);
        this.people.add(person);
        return person;
    }

    @Override
    public void addPersonToTeam(Person person, Team team) {
        team.getPeople().add(person);
    }

    @Override
    public Board createBoard(String boardName) {
        Board board = new BoardImpl(boardName);
        this.boards.add(board);
        return board;
    }

    @Override
    public Bug createBug(String title, String description, List<String> steps, PriorityEnum priorityEnum,
                         BugSeverityEnum bugSeverityEnum, BugStatusEnum bugStatusEnum, Person assignee) {
        Bug bug = new BugImpl(++nextId, title, description, steps, priorityEnum, bugSeverityEnum, bugStatusEnum, assignee);
        this.tasks.add(bug);
        return bug;
    }

    @Override
    public Story createStory(String title, String description, PriorityEnum priorityEnum, StorySizeEnum storySizeEnum,
                             StoryStatusEnum storyStatusEnum) {
        Story story = new StoryImpl(++nextId, title, description, priorityEnum, storySizeEnum, storyStatusEnum);
        this.tasks.add(story);
        return story;
    }

    @Override
    public Feedback createFeedback(String title, String description, int rating, FeedbackStatusEnum feedbackStatusEnum) {
        Feedback feedback = new FeedbackImpl(++nextId, title, description, rating, feedbackStatusEnum);
        this.tasks.add(feedback);
        return feedback;
    }

    @Override
    public Team createTeam(String teamName) {
        Team team = new TeamImpl(teamName);
        this.teams.add(team);
        return team;

    }
}
