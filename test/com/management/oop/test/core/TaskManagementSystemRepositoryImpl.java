package com.management.oop.test.core;

import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.contracts.Team;
import com.management.oop.test.models.PersonImplTests;
import com.management.oop.test.models.TeamImplTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskManagementSystemRepositoryImpl {
    private TaskManagementSystemRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new com.management.oop.project.core.TaskManagementSystemRepositoryImpl();
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
        Assertions.assertEquals(1, repository.getTeams().size());
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

    /*
        //TODO task and board exist
        @Test
        public void boardExists_Should_ReturnTrue_When_BoardExists() {
            // Arrange
    //        repository.createBoard(TaskBaseConstants.VALID_BOARD_NAME,
    //                TeamImplTests.VALID_TEAM_NAME);

            // Act, Assert
           // Assertions.assertTrue(repository.boardExist(TaskBaseConstants.VALID_BOARD_NAME));
        }

        //TODO
        @Test
        public void boardExists_Should_ReturnFalse_When_BoardDoesNotExist() {
            // Arrange
    //        repository.createBoard(TaskBaseConstants.VALID_BOARD_NAME,
    //                TeamImplTests.VALID_TEAM_NAME);

            // Act, Assert
    //        Assertions.assertFalse(repository.boardExist("aaa"));
        }
        //TODO
        @Test
        public void taskExists_Should_ReturnTrue_When_TaskExists() {
            // Arrange
          repository.taskExist(1);

            // Act, Assert
             Assertions.assertTrue(repository.taskExist(1));
        }
        //TODO
        @Test
        public void taskExists_Should_ReturnFalse_When_TaskDoesNotExist() {
            // Arrange
            repository.cr(TaskBaseConstants.VALID_BOARD_NAME,
                    TeamImplTests.VALID_TEAM_NAME);

            // Act, Assert
            Assertions.assertFalse(repository.boardExist("aaa"));
        }
    */
    @Test
    public void createTeam_Should_AddToTeam_When_ArgumentsAreValid() {
        // Arrange
        repository.createTeam(
                TeamImplTests.VALID_TEAM_NAME);

        // Act, Assert
        Assertions.assertEquals(1, repository.getTeams().size());
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
    public void findTeamByName_Should_ReturnPerson_When_Exists() {
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
