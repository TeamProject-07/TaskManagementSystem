package com.management.oop.test.core;

import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.*;
import com.management.oop.project.models.enums.*;
import com.management.oop.project.models.tasks.BugImpl;
import com.management.oop.project.models.tasks.FeedbackImpl;
import com.management.oop.project.models.tasks.StoryImpl;
import com.management.oop.test.models.PersonImplTests;
import com.management.oop.test.models.TeamImplTests;
import com.management.oop.test.utils.TaskBaseConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskManagementSystemRepositoryImplTest {
    private TaskManagementSystemRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new com.management.oop.project.core.TaskManagementSystemRepositoryImpl();
        repository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);
        repository.createBoard(TaskBaseConstants.VALID_BOARD_NAME, TaskBaseConstants.VALID_TEAM_NAME);
        repository.createBug(TaskBaseConstants.VALID_BOARD_NAME, TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,null, PriorityEnum.MEDIUM, BugSeverityEnum.CRITICAL);

    }

    @Test
    public void constructor_Should_InitializeTeams() {
        // Arrange, Act, Assert
        Assertions.assertNotNull(repository.getTeams());
    }

    @Test
    public void constructor_Should_InitializePeople() {
        // Arrange, Act, Assert
        Assertions.assertNotNull(repository.getPeople());
    }

    @Test
    public void getPeople_Should_ReturnCopyOfCollection() {
        // Arrange
        repository.createPerson(PersonImplTests.VALID_USERNAME);
        repository.getPeople().clear();

        // Act, Assert
        Assertions.assertEquals(1, repository.getPeople().size());
    }

    @Test
    public void getTeams_Should_ReturnCopyOfCollection() {
        // Arrange
        repository.createTeam(
                TeamImplTests.VALID_TEAM_NAME);

        repository.getTeams().clear();

        // Act, Assert
        Assertions.assertEquals(2, repository.getTeams().size());
    }

    @Test
    public void personExists_Should_ReturnTrue_When_PersonExists() {
        // Arrange
        repository.createPerson(PersonImplTests.VALID_USERNAME);

        // Act, Assert
        Assertions.assertTrue(repository.personExist(PersonImplTests.VALID_USERNAME));
    }

    @Test
    public void personExists_Should_ReturnFalse_When_PersonDoesNotExist() {
        // Arrange
        repository.createPerson(PersonImplTests.VALID_USERNAME);

        // Act, Assert
        Assertions.assertFalse(repository.personExist("aaa"));
    }

    @Test
    public void teamExists_Should_ReturnTrue_When_TeamExists() {
        // Arrange
        repository.createTeam(
                TeamImplTests.VALID_TEAM_NAME);

        // Act, Assert
        Assertions.assertTrue(repository.teamExist(TeamImplTests.VALID_TEAM_NAME));
    }

    @Test
    public void teamExists_Should_ReturnFalse_When_TeamDoesNotExist() {
        // Arrange
        repository.createTeam(
                TeamImplTests.VALID_TEAM_NAME);

        // Act, Assert
        Assertions.assertFalse(repository.teamExist("aaa"));
    }
    @Test
    public void boardExists_Should_ReturnTrue_When_BoardExists() {
        // Arrange
        repository.createBoard(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TEAM_NAME);

        // Act, Assert
        Assertions.assertTrue(repository.boardExist(TaskBaseConstants.VALID_BOARD_NAME));
    }
    @Test
    public void boardExists_Should_ReturnFalse_When_BoardDoesNotExist() {
        // Arrange
        repository.createBoard(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TEAM_NAME);

        // Act, Assert
        Assertions.assertFalse(repository.boardExist("aaa"));
    }
    @Test
    public void createTeam_Should_AddToTeam_When_ArgumentsAreValid() {
        // Arrange
        repository.createTeam(
                TeamImplTests.VALID_TEAM_NAME);

        // Act, Assert
        Assertions.assertEquals(2, repository.getTeams().size());
    }

    @Test
    public void createPerson_Should_AddToPeople_When_ArgumentsAreValid() {
        // Arrange
        repository.createPerson(PersonImplTests.VALID_USERNAME);

        // Act, Assert
        Assertions.assertEquals(1, repository.getPeople().size());
    }

    /*
    //TODO createStory, createFeedback

    @Test
    public void createBug_Should_AddToBug_When_ArgumentsAreValid() {
        List<String> steps = new ArrayList<>();
        // Arrange
        repository.createBug(TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                steps,
                PriorityEnum.HIGH,
                BugSeverityEnum.CRITICAL);

        // Act, Assert
        Assertions.assertEquals(1, repository.getAllBugs().size());
    }
    */
    @Test
    public void findPersonByName_Should_ReturnPerson_When_Exists() {
        // Arrange
        String personName = PersonImplTests.VALID_USERNAME;
        repository.createPerson(
                PersonImplTests.VALID_USERNAME);

        // Act
        Person found = repository.findPersonByName(personName);

        // Assert
        Assertions.assertEquals(found.getName(), personName);
    }

    @Test
    public void findPersonByName_Should_ThrowException_When_DoesNotExist() {
        // Arrange, Act, Assert
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> repository.findPersonByName("aaa"));
    }
    @Test
    public void findBugById_Should_ReturnId_When_Exists() {
        // Arrange
        BugImpl bug = new BugImpl(1, TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION, null,
                PriorityEnum.HIGH, BugSeverityEnum.CRITICAL);
        repository.createBug(TaskBaseConstants.VALID_BOARD_NAME, TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION, null, PriorityEnum.HIGH,
                BugSeverityEnum.CRITICAL);

        // Act
        Bug found = repository.findBugById(bug.getId());

        // Assert
        Assertions.assertEquals(found.getId(), bug.getId());
    }

    @Test
    public void findBugById_Should_ThrowException_When_DoesNotExist() {
        // Arrange, Act, Assert
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> repository.findBugById(Integer.parseInt("Id")));
    }
    @Test
    public void findStoryById_Should_ReturnId_When_Exists() {
        // Arrange
        StoryImpl story = new StoryImpl(2, TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION, PriorityEnum.HIGH, StorySizeEnum.MEDIUM,
                StoryStatusEnum.IN_PROGRESS);
        repository.createStory(TaskBaseConstants.VALID_BOARD_NAME, TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,  PriorityEnum.HIGH,
                StorySizeEnum.MEDIUM, StoryStatusEnum.IN_PROGRESS);

        // Act
        Story found = repository.findStoryById(story.getId());

        // Assert
        Assertions.assertEquals(found.getId(), story.getId());
    }
    @Test
    public void findStoryById_Should_ThrowException_When_DoesNotExist() {
        // Arrange, Act, Assert
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> repository.findStoryById(Integer.parseInt("Id")));
    }
    @Test
    public void findFeedbackById_Should_ReturnId_When_Exists() {
        // Arrange
        FeedbackImpl feedback = new FeedbackImpl(2, TaskBaseConstants.VALID_TITLE, TaskBaseConstants.VALID_DESCRIPTION,
                2, FeedbackStatusEnum.SCHEDULED);
        repository.createFeedback(TaskBaseConstants.VALID_BOARD_NAME, TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,  2,
                FeedbackStatusEnum.SCHEDULED);

        // Act
        Feedback found = repository.findFeedbackById(feedback.getId());

        // Assert
        Assertions.assertEquals(found.getId(), feedback.getId());
    }
    @Test
    public void findFeedbackById_Should_ThrowException_When_DoesNotExist() {
        // Arrange, Act, Assert
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> repository.findFeedbackById(Integer.parseInt("Id")));
    }

    @Test
    public void findBoardByName_Should_ReturnBoard_When_Exists() {
        // Arrange
        String boardName = TaskBaseConstants.VALID_BOARD_NAME;
        repository.createBoard(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TEAM_NAME);

        // Act
        Board found = repository.findBoardByName(boardName);

        // Assert
        Assertions.assertEquals(found.getName(), boardName);
    }
    @Test
    public void findBoardByName_Should_ThrowException_When_DoesNotExist() {
        // Arrange, Act, Assert
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> repository.findBoardByName("aaa"));
    }

    @Test
    public void findTeamByName_Should_ReturnTeam_When_Exists() {
        // Arrange
        String teamName = TeamImplTests.VALID_TEAM_NAME;
        repository.createTeam(
                TeamImplTests.VALID_TEAM_NAME);

        // Act
        Team found = repository.findTeamByName(teamName);

        // Assert
        Assertions.assertEquals(found.getName(), teamName);
    }

    @Test
    public void findTeamByName_Should_ThrowException_When_DoesNotExist() {
        // Arrange, Act, Assert
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> repository.findTeamByName("aaa"));
    }

}
