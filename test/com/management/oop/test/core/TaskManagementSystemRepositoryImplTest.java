package com.management.oop.test.core;

import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.*;
import com.management.oop.project.models.enums.*;
import com.management.oop.project.models.tasks.BugImpl;
import com.management.oop.project.models.tasks.FeedbackImpl;
import com.management.oop.project.models.tasks.StoryImpl;
import com.management.oop.test.models.PersonImplTests;
import com.management.oop.test.utils.TaskBaseConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskManagementSystemRepositoryImplTest {
    private TaskManagementSystemRepository taskManagementSystemRepository;

    @BeforeEach
    public void before() {
        taskManagementSystemRepository = new com.management.oop.project.core.TaskManagementSystemRepositoryImpl();
        taskManagementSystemRepository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);
        taskManagementSystemRepository.createBoard(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TEAM_NAME);
        taskManagementSystemRepository.createBug(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                TaskBaseConstants.STEPS,
                PriorityEnum.MEDIUM,
                BugSeverityEnum.CRITICAL);

    }

    @Test
    public void constructor_Should_InitializeTeams() {
        // Arrange, Act, Assert
        Assertions.assertNotNull(taskManagementSystemRepository.getTeams());
    }

    @Test
    public void constructor_Should_InitializePeople() {
        // Arrange, Act, Assert
        Assertions.assertNotNull(taskManagementSystemRepository.getPeople());
    }

    @Test
    public void getPeople_Should_ReturnCopyOfCollection() {
        // Arrange
        taskManagementSystemRepository.createPerson(PersonImplTests.VALID_USERNAME);
        taskManagementSystemRepository.getPeople().clear();

        // Act, Assert
        Assertions.assertEquals(1, taskManagementSystemRepository.getPeople().size());
    }

    @Test
    public void getTeams_Should_ReturnCopyOfCollection() {
        // Arrange
        taskManagementSystemRepository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);

        taskManagementSystemRepository.getTeams().clear();

        // Act, Assert
        Assertions.assertEquals(2, taskManagementSystemRepository.getTeams().size());
    }

    @Test
    public void personExists_Should_ReturnTrue_When_PersonExists() {
        // Arrange
        taskManagementSystemRepository.createPerson(PersonImplTests.VALID_USERNAME);

        // Act, Assert
        Assertions.assertTrue(taskManagementSystemRepository.personExist(PersonImplTests.VALID_USERNAME));
    }

    @Test
    public void personExists_Should_ReturnFalse_When_PersonDoesNotExist() {
        // Arrange
        taskManagementSystemRepository.createPerson(PersonImplTests.VALID_USERNAME);

        // Act, Assert
        Assertions.assertFalse(taskManagementSystemRepository.personExist("aaa"));
    }

    @Test
    public void teamExists_Should_ReturnTrue_When_TeamExists() {
        // Arrange
        taskManagementSystemRepository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);

        // Act, Assert
        Assertions.assertTrue(taskManagementSystemRepository.teamExist(TaskBaseConstants.VALID_TEAM_NAME));
    }

    @Test
    public void teamExists_Should_ReturnFalse_When_TeamDoesNotExist() {
        // Arrange
        taskManagementSystemRepository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);

        // Act, Assert
        Assertions.assertFalse(taskManagementSystemRepository.teamExist(TaskBaseConstants.INVALID_TEAM_NAME));
    }
    @Test
    public void boardExists_Should_ReturnTrue_When_BoardExists() {
        // Arrange
        taskManagementSystemRepository.createBoard(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TEAM_NAME);

        // Act, Assert
        Assertions.assertTrue(taskManagementSystemRepository.boardExist(TaskBaseConstants.VALID_BOARD_NAME));
    }
    @Test
    public void boardExists_Should_ReturnFalse_When_BoardDoesNotExist() {
        // Arrange
        taskManagementSystemRepository.createBoard(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TEAM_NAME);

        // Act, Assert
        Assertions.assertFalse(taskManagementSystemRepository.boardExist(TaskBaseConstants.INVALID_BOARD_NAME));
    }
    @Test
    public void createTeam_Should_AddToTeam_When_ArgumentsAreValid() {
        // Arrange
        taskManagementSystemRepository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);

        // Act, Assert
        Assertions.assertEquals(2, taskManagementSystemRepository.getTeams().size());
    }

    @Test
    public void createPerson_Should_AddToPeople_When_ArgumentsAreValid() {
        // Arrange
        taskManagementSystemRepository.createPerson(PersonImplTests.VALID_USERNAME);

        // Act, Assert
        Assertions.assertEquals(1, taskManagementSystemRepository.getPeople().size());
    }

    @Test
    public void findPersonByName_Should_ReturnPerson_When_Exists() {
        // Arrange
        String personName = PersonImplTests.VALID_USERNAME;
        taskManagementSystemRepository.createPerson(PersonImplTests.VALID_USERNAME);

        // Act
        Person found = taskManagementSystemRepository.findPersonByName(personName);

        // Assert
        Assertions.assertEquals(found.getName(), personName);
    }

    @Test
    public void findPersonByName_Should_ThrowException_When_DoesNotExist() {
        // Arrange, Act, Assert
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> taskManagementSystemRepository.findPersonByName("aaa"));
    }
    @Test
    public void findBugById_Should_ReturnId_When_Exists() {
        // Arrange
        BugImpl bug = new BugImpl(
                1,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION, null,
                PriorityEnum.HIGH, BugSeverityEnum.CRITICAL);
        taskManagementSystemRepository.createBug(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                TaskBaseConstants.STEPS,
                PriorityEnum.HIGH,
                BugSeverityEnum.CRITICAL);

        // Act
        Bug found = taskManagementSystemRepository.findBugById(bug.getId());

        // Assert
        Assertions.assertEquals(found.getId(), bug.getId());
    }

    @Test
    public void findBugById_Should_ThrowException_When_DoesNotExist() {
        // Arrange, Act, Assert
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> taskManagementSystemRepository.findBugById(Integer.parseInt("Id")));
    }
    @Test
    public void findStoryById_Should_ReturnId_When_Exists() {
        // Arrange
        StoryImpl story = new StoryImpl(
                2,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                PriorityEnum.HIGH, StorySizeEnum.MEDIUM,
                StoryStatusEnum.IN_PROGRESS);
        taskManagementSystemRepository.createStory(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                PriorityEnum.HIGH,
                StorySizeEnum.MEDIUM,
                StoryStatusEnum.IN_PROGRESS);

        // Act
        Story found = taskManagementSystemRepository.findStoryById(story.getId());

        // Assert
        Assertions.assertEquals(found.getId(), story.getId());
    }
    @Test
    public void findStoryById_Should_ThrowException_When_DoesNotExist() {
        // Arrange, Act, Assert
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> taskManagementSystemRepository.findStoryById(Integer.parseInt("Id")));
    }
    @Test
    public void findFeedbackById_Should_ReturnId_When_Exists() {
        // Arrange
        FeedbackImpl feedback = new FeedbackImpl(
                2,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                TaskBaseConstants.VALID_RATING,
                FeedbackStatusEnum.SCHEDULED);
        taskManagementSystemRepository.createFeedback(
                TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                TaskBaseConstants.VALID_RATING,
                FeedbackStatusEnum.SCHEDULED);

        // Act
        Feedback found = taskManagementSystemRepository.findFeedbackById(feedback.getId());

        // Assert
        Assertions.assertEquals(found.getId(), feedback.getId());
    }
    @Test
    public void findFeedbackById_Should_ThrowException_When_DoesNotExist() {
        // Arrange, Act, Assert
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> taskManagementSystemRepository.findFeedbackById(Integer.parseInt("Id")));
    }

    @Test
    public void findBoardByName_Should_ReturnBoard_When_Exists() {
        // Arrange
        String boardName = TaskBaseConstants.VALID_BOARD_NAME;
        taskManagementSystemRepository.createBoard(TaskBaseConstants.VALID_BOARD_NAME,
                TaskBaseConstants.VALID_TEAM_NAME);

        // Act
        Board found = taskManagementSystemRepository.findBoardByName(boardName);

        // Assert
        Assertions.assertEquals(found.getName(), boardName);
    }
    @Test
    public void findBoardByName_Should_ThrowException_When_DoesNotExist() {
        // Arrange, Act, Assert
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> taskManagementSystemRepository.findBoardByName("aaa"));
    }

    @Test
    public void findTeamByName_Should_ReturnTeam_When_Exists() {
        // Arrange
        String teamName = TaskBaseConstants.VALID_TEAM_NAME;
        taskManagementSystemRepository.createTeam(TaskBaseConstants.VALID_TEAM_NAME);

        // Act
        Team found = taskManagementSystemRepository.findTeamByName(teamName);

        // Assert
        Assertions.assertEquals(found.getName(), teamName);
    }

    @Test
    public void findTeamByName_Should_ThrowException_When_DoesNotExist() {
        // Arrange, Act, Assert
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> taskManagementSystemRepository.findTeamByName("aaa"));
    }

}
