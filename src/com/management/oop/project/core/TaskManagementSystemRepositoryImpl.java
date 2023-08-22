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
import java.util.stream.Collectors;

public class TaskManagementSystemRepositoryImpl implements TaskManagementSystemRepository {
    public static final String PERSON_NOT_FOUND = "Person not found.";
    public static final String TEAM_NOT_FOUND = "Team not found.";
    public static final String NO_BUG_WITH_THIS_ID = "There is no bug with this ID.";
    public static final String NO_STORY_WITH_THIS_ID = "There is no story with this ID.";
    public static final String NO_FEEDBACK_WITH_THIS_ID = "There is no feedback with this ID.";
    public static final String NO_TASK_WITH_THIS_ID = "There is no task with this ID.";
    public static final String NO_BOARD_WITH_THIS_NAME = "There is no board with this name.";
    public static final String NOT_ASSIGNABLE_TASK = "Task is not assignable.";
    public static final String ASSIGNED_TASK = "Task is already assigned.";

    private int nextId;
    private final List<Team> teams;
    private final List<Person> people;

    public TaskManagementSystemRepositoryImpl() {
        this.teams = new ArrayList<>();
        this.people = new ArrayList<>();
        nextId = 0;
    }

    @Override
    public Team createTeam(String teamName) {
        Team team = new TeamImpl(teamName);
        this.teams.add(team);
        return team;
    }

    @Override
    public Board createBoard(String boardName, String teamName) {

        Board board = new BoardImpl(boardName);
        findTeamByName(teamName).addBoard(board);
        return board;
    }

    @Override
    public boolean personExist(String personName) {
        return people
                .stream()
                .anyMatch(person -> person.getName().equalsIgnoreCase(personName));
    }

    @Override
    public boolean personHasTeam(String personName) {
        return teams
                .stream()
                .flatMap(team -> team.getPeople().stream())
                .anyMatch(person -> person.getName().equalsIgnoreCase(personName));
    }

    @Override
    public Person findPersonByName(String personName) {
        return people
                .stream()
                .filter(person -> person.getName().equalsIgnoreCase(personName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(PERSON_NOT_FOUND));
    }

    @Override
    public Bug findBugById(int id) {
        return getAllBugs()
                .stream()
                .filter(bug -> bug.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_BUG_WITH_THIS_ID));
    }

    @Override
    public Story findStoryById(int id) {
        return getAllStories()
                .stream()
                .filter(story -> story.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_STORY_WITH_THIS_ID));
    }

    @Override
    public Feedback findFeedbackById(int id) {
        return getAllFeedback()
                .stream()
                .filter(feedback -> feedback.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_FEEDBACK_WITH_THIS_ID));
    }

    @Override
    public Task findTaskById(int id) {
        return getAllTasks()
                .stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_TASK_WITH_THIS_ID));
    }

    @Override
    public Board findBoardByName(String boardName) {
        return getAllBoards()
                .stream()
                .filter(board -> board.getName().equalsIgnoreCase(boardName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_BOARD_WITH_THIS_NAME));
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
    public Team findTeamByName(String teamName) {
        return teams
                .stream()
                .filter(team -> team.getName().equalsIgnoreCase(teamName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(TEAM_NOT_FOUND));
    }

    @Override
    public boolean boardExist(String boardName) {
        return getAllBoards()
                .stream()
                .anyMatch(board -> board.getName().equalsIgnoreCase(boardName));
    }

    @Override
    public boolean taskExist(int id) {
        return getAllTasks()
                .stream()
                .anyMatch(task -> task.getId() == id);
    }

    @Override
    public boolean teamExist(String teamName) {
        return getTeams()
                .stream()
                .anyMatch(team -> team.getName().equalsIgnoreCase(teamName));
    }

    @Override
    public Person createPerson(String name) {
        Person person = new PersonImpl(name);
        this.people.add(person);
        return person;
    }

    @Override
    public void addPersonToTeam(Person person, Team team) {
        team.addPerson(person);
    }


    @Override
    public Bug createBug(String boardName, String title,
                         String description, List<String> steps,
                         PriorityEnum priorityEnum, BugSeverityEnum bugSeverityEnum) {
        Bug bug = new BugImpl(++nextId, title, description, steps, priorityEnum, bugSeverityEnum);
        findBoardByName(boardName).addBug(bug);
        return bug;
    }

    @Override
    public Story createStory(String boardName, String title,
                             String description, PriorityEnum priorityEnum,
                             StorySizeEnum storySizeEnum, StoryStatusEnum storyStatusEnum) {
        Story story = new StoryImpl(++nextId, title, description, priorityEnum, storySizeEnum, storyStatusEnum);
        findBoardByName(boardName).addStory(story);
        return story;
    }


    @Override
    public Feedback createFeedback(String boardName, String title,
                                   String description, int rating,
                                   FeedbackStatusEnum feedbackStatusEnum) {
        Feedback feedback = new FeedbackImpl(++nextId, title, description, rating, feedbackStatusEnum);
        findBoardByName(boardName).addFeedback(feedback);
        return feedback;
    }

    @Override
    public List<Task> getAllTasks() {
        return teams.stream()
                .flatMap(team -> team.getBoards().stream())
                .flatMap(board -> board.getTasks().stream())
                .collect(Collectors.toList());
    }

    @Override
    public List<Bug> getAllBugs() {
        return teams.stream()
                .flatMap(team -> team.getBoards().stream())
                .flatMap(board -> board.getBugs().stream())
                .collect(Collectors.toList());
    }

    @Override
    public List<Feedback> getAllFeedback() {
        return teams.stream()
                .flatMap(team -> team.getBoards().stream())
                .flatMap(board -> board.getFeedbacks().stream())
                .collect(Collectors.toList());

    }

    @Override
    public List<Story> getAllStories() {
        return teams.stream()
                .flatMap(team -> team.getBoards().stream())
                .flatMap(board -> board.getStories().stream())
                .collect(Collectors.toList());
    }

    @Override
    public List<Assignable> getAllAssignableTasks() {
        List<Assignable> assignableTasks = new ArrayList<>();
        assignableTasks.addAll(getAllBugs());
        assignableTasks.addAll(getAllStories());
        return assignableTasks;
    }

    @Override
    public boolean ifTaskIsAssignable(int id) {
        taskExist(id);
        List<Assignable> tasks = getAllAssignableTasks();
        for (Task task : tasks) {
            if (task.getId() == id) {
                return true;
            }
        }
        throw new IllegalArgumentException(NOT_ASSIGNABLE_TASK);
    }

    @Override
    public boolean ifTaskIsNotAssigned(Assignable task) {
        if (task.getAssignee() != null) {
            throw new IllegalArgumentException(ASSIGNED_TASK);
        }
        return true;
    }

    @Override
    public Assignable findAssignableTaskById(int id) {
        List<Assignable> tasks = getAllAssignableTasks();
        for (Assignable task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        throw new IllegalArgumentException(NO_TASK_WITH_THIS_ID);
    }

    @Override
    public List<Board> getAllBoards() {
        return teams.stream()
                .flatMap(team -> team.getBoards().stream())
                .collect(Collectors.toList());
    }

    public List<Assignable> getTasksWithAssignee() {
        List<Assignable> assigneeTask = new ArrayList<>();
        for (Assignable task : getAllAssignableTasks()) {
            if (task.getAssignee() != null) {
                assigneeTask.add(task);
            }
        }
        return assigneeTask;
    }


}


