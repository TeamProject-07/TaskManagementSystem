package com.management.oop.test.models;

import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.TeamImpl;
import com.management.oop.project.models.contracts.Board;
import com.management.oop.project.models.contracts.Historytable;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.contracts.Team;
import com.management.oop.project.models.enums.FeedbackStatusEnum;
import com.management.oop.project.models.tasks.FeedbackImpl;
import com.management.oop.test.utils.TaskBaseConstants;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TeamImplTests {
    public static final int NAME_MIN_LENGTH = 5;
    public static final int NAME_MAX_LENGTH = 15;
    public static final String INVALID_TEAM_NAME = TestUtilities.getString(NAME_MAX_LENGTH + 1);

    public static final String VALID_TEAM_NAME = TestUtilities.getString(NAME_MIN_LENGTH + 1);
    @Test
    public void teamImpl_Should_ImplementTeamInterface() {
        //Arrange, act
        TeamImpl team = initializeTestTeam();
        //Assert
        Assertions.assertTrue(team instanceof Team);
    }
    @Test
    public void constructor_Should_ThrowException_When_TitleNameLengthOutOfBounds() {
        // Arrange, Act, Assert
        assertThrows(IllegalArgumentException.class, () -> new TeamImpl(INVALID_TEAM_NAME));
    }
    @Test
    public void construct_Should_CreateTeam_When_NameIsValid() {
        // Arrange, Act, Assert
        assertDoesNotThrow(() -> new TeamImpl(VALID_TEAM_NAME));
    }
    @Test
    public void construct_Should_InitializeNewListOfPerson_When_TeamIsCreated() {
        // Arrange, Act
        Team team = initializeTestTeam();

        // Assert
        assertNotNull(team.getPeople());
    }
    @Test
    public void construct_Should_InitializeNewListOfBoard_When_TeamIsCreated() {
        // Arrange, Act
        Team team = initializeTestTeam();

        // Assert
        assertNotNull(team.getBoards());
    }
    @Test
    public void construct_Should_InitializeNewListOfHistories_When_TeamIsCreated() {
        // Arrange, Act
        Team team = initializeTestTeam();

        // Assert
        assertNotNull(team.getHistory());
    }
    @Test
    public void add_Should_AddPerson_When_PersonIsValid() {
        // Arrange
        Team team = initializeTestTeam();
       // Person personToAdd = initializeTestPerson();

        // Act, Assert
//        assertAll(
//                () -> assertDoesNotThrow(() -> team.addPerson(personToAdd)),
//                () -> assertEquals(1, team.getPeople().size())
//        );
    }
    @Test
    public void add_Should_AddBoard_When_BoardIsValid() {
        // Arrange
        Team team = initializeTestTeam();
      //  Board boardToAdd = initializeTestBoard();

         //Act, Assert
//        assertAll(
//                () -> assertDoesNotThrow(() -> team.addBoard(boardToAdd)),
//                () -> assertEquals(1, team.getBoards().size())
//        );
    }

    @Test
    public void construct_Should_InitializeNewListOfPeople_When_TeamsIsCreated() {
        // Arrange, Act
        Team team = initializeTestTeam();

        // Assert
        assertNotNull(team.getPeople());
    }

    @Test
    public void construct_Should_InitializeNewListOfBoard_When_TeamsIsCreated() {
        // Arrange, Act
        Team team = initializeTestTeam();

        // Assert
        assertNotNull(team.getBoards());
    }

    //for commands
    public static Team addInitializedTeamToRepository
            (TaskManagementSystemRepository repository) {
        return repository.createTeam(VALID_TEAM_NAME);
    }

        public static TeamImpl initializeTestTeam() {
        return new TeamImpl(VALID_TEAM_NAME);
    }
}
