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
    public Bug findBugById(int id) {
        for (Team team : teams) {
            for (Board board : team.getBoards()) {
                for (Bug bug : board.getBugs()) {
                    if (bug.getId() == id) {
                        return bug;
                    }
                }
            }
        }
        throw new IllegalArgumentException("There is no bug with this Id.");
    }

    @Override
    public Story findStoryById(int id) {
        for (Team team : teams) {
            for (Board board : team.getBoards()) {
                for (Story story : board.getStories()) {
                    if (story.getId() == id) {
                        return story;
                    }
                }
            }
        }
        throw new IllegalArgumentException("There is no story with this Id.");
    }

    @Override
    public Feedback findFeedbackById(int id) {
        for (Team team : teams) {
            for (Board board : team.getBoards()) {
                for (Feedback feedback : board.getFeedbacks()) {
                    if (feedback.getId() == id) {
                        return feedback;
                    }
                }
            }
        }
        throw new IllegalArgumentException("There is no bug with this Id.");
    }

    @Override
    public Task findTaskById(int id) {
        for (Team team : teams) {
            for (Board board : team.getBoards()) {
                for (Task task : board.getTasks()) {
                    if (task.getId() == id) {
                        return task;
                    }
                }
            }
        }
        throw new IllegalArgumentException("There is no task with this id.");
    }

    @Override
    public Board findBoardByName(String boardName) {
        for (Team team : teams) {
            for (Board board : team.getBoards()) {
                if (board.getName().equalsIgnoreCase(boardName)) {
                    return board;
                }
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
        for (Team team : teams) {
            for (Board board : team.getBoards()) {
                if (board.getName().equalsIgnoreCase(boardName)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean taskExist(int id) {
        for (Team team : teams) {
            for (Board board : team.getBoards()) {
                for (Task task : board.getTasks()) {
                    if (task.getId() == id) {
                        return true;
                    }
                }
            }
        }
        throw new IllegalArgumentException("There is no task with this id.");
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
    public Bug createBug(String boardName, String title, String description, List<String> steps, PriorityEnum priorityEnum,
                         BugSeverityEnum bugSeverityEnum) {
        Bug bug = new BugImpl(++nextId, title, description, steps, priorityEnum, bugSeverityEnum);
        findBoardByName(boardName).addBug(bug);
        return bug;
    }

    @Override
    public Story createStory(String boardName, String title, String description, PriorityEnum priorityEnum, StorySizeEnum storySizeEnum,
                             StoryStatusEnum storyStatusEnum) {
        Story story = new StoryImpl(++nextId, title, description, priorityEnum, storySizeEnum, storyStatusEnum);
        findBoardByName(boardName).addStory(story);
        return story;
    }


    @Override
    public Feedback createFeedback(String boardName, String title, String description, int rating, FeedbackStatusEnum feedbackStatusEnum) {
        Feedback feedback = new FeedbackImpl(++nextId, title, description, rating, feedbackStatusEnum);
        findBoardByName(boardName).addFeedback(feedback);
        return feedback;
    }

    @Override
    public void addComment(String message, Person author) {
    }


    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        for (Team team : teams) {
            for (Board board : team.getBoards()) {
                board.getTasks().addAll(tasks);
            }
        }
        return tasks;
    }

    @Override
    public List<Bug> getAllBugs() {
        List<Bug> bugs = new ArrayList<>();
        for (Team team : teams) {
            for (Board board : team.getBoards()) {
                board.getBugs().addAll(bugs);
            }
        }
        return bugs;
    }

    @Override
    public List<Feedback> getAllFeedback() {
        List<Feedback> feedbacks = new ArrayList<>();
        for (Team team : teams) {
            for (Board board: team.getBoards()) {
               feedbacks.addAll(board.getFeedbacks());
            }
        }
        return feedbacks;
    }

    @Override
    public List<Story> getAllStories() {
        List<Story> stories = new ArrayList<>();
        for (Team team : teams) {
            for (Board board: team.getBoards()) {
                board.getStories().addAll(stories);
            }
        }
        return stories;
    }

    @Override
    public List<Board> getAllBoards() {
        List<Board>boards=new ArrayList<>();
        for (Team team : teams) {
            team.getBoards().addAll(boards);
        }
        return boards;
    }
}

