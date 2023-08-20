package com.management.oop.test.commands.listing;

import com.management.oop.project.commands.listing.SortAllTasksByTitle;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.BoardImpl;
import com.management.oop.project.models.TeamImpl;
import com.management.oop.project.models.contracts.Board;
import com.management.oop.project.models.contracts.Feedback;
import com.management.oop.project.models.contracts.Task;
import com.management.oop.project.models.contracts.Team;
import com.management.oop.project.models.enums.*;
import com.management.oop.project.models.tasks.BugImpl;
import com.management.oop.project.models.tasks.FeedbackImpl;
import com.management.oop.project.models.tasks.StoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortAllTasksByTitleTest {
    private TaskManagementSystemRepository repository;

    private SortAllTasksByTitle sortAllTasksByTitle;

    private Task task;

    private List<Task> tasks;

    private List<String> params;

    private List<String> sortedTasks;

    private Board board;

    private Team team;


    @BeforeEach
    public void before() {
        repository = new TaskManagementSystemRepositoryImpl();
        sortAllTasksByTitle = new SortAllTasksByTitle(repository);
        List<String> params = new ArrayList<>();
       // Team team = new TeamImpl("teamName");
       // Board board = new BoardImpl("boardName");
        repository.createTeam("teamName");
        repository.createBoard("boardName", "teamName");
        repository.createFeedback("boardName","Zzzzzzzzzzzz", "description", 25, FeedbackStatusEnum.NEW);
        repository.createBug("boardName", "VerySeriousProblem", "description", List.of("step1", "step2"), PriorityEnum.HIGH, BugSeverityEnum.CRITICAL );
        repository.createStory("boardName",  "Aaaaaaaaaaaaaa", "description", PriorityEnum.MEDIUM, StorySizeEnum.LARGE, StoryStatusEnum.IN_PROGRESS);

     //  Task task1 = new FeedbackImpl(1, "Zzzzzzzzzzzz", "description", 25, FeedbackStatusEnum.NEW);
     //  Task task2 = new BugImpl(2, "VerySeriousProblem", "description", List.of("step1", "step2"), PriorityEnum.HIGH, BugSeverityEnum.CRITICAL);
     //  Task task3 = new StoryImpl(3, "Aaaaaaaaaaaaaa", "description", PriorityEnum.MEDIUM, StorySizeEnum.LARGE, StoryStatusEnum.IN_PROGRESS);


    }

    @Test
    public void should_SortTasks_WhenArguments_AreValid() {
      //  List<Task> tasks = new ArrayList<>();
      //List<String> params = new ArrayList<>();
      //  Task task1 = new FeedbackImpl(1, "Zzzzzzzzzzzz", "description", 25, FeedbackStatusEnum.NEW);
      //  Task task2 = new BugImpl(2, "VerySeriousProblem", "description", List.of("step1", "step2"), PriorityEnum.HIGH, BugSeverityEnum.CRITICAL);
      //  Task task3 = new StoryImpl(3, "Aaaaaaaaaaaaaa", "description", PriorityEnum.MEDIUM, StorySizeEnum.LARGE, StoryStatusEnum.IN_PROGRESS);
//
      //  tasks.add(task1);
      //  tasks.add(task2);
      //  tasks.add(task3);

       String result = sortAllTasksByTitle.execute(params);

     //   String[] resultArray = result.split(",");
     //   List<String> titlesList = Arrays.asList(resultArray);
     //   Assertions.assertEquals(3, tasks.size());
      // Assertions.assertEquals("Aaaaaaaaaaaaaa", titlesList.get(1));
      // Assertions.assertEquals("VerySeriousProblem", titlesList.get(2));
      // Assertions.assertEquals("Zzzzzzzzzzzz", titlesList.get(3));

        Assertions.assertEquals("[Aaaaaaaaaaaaaa, VerySeriousProblem, Zzzzzzzzzzzz]", result);
    }
}
