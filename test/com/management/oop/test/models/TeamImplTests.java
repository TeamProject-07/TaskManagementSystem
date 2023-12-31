package com.management.oop.test.models;

import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.PersonImpl;
import com.management.oop.project.models.TeamImpl;
import com.management.oop.project.models.contracts.Board;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.contracts.Team;
import com.management.oop.test.utils.TaskBaseConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.management.oop.test.models.BoardImplTests.initializeTestBoard;
import static com.management.oop.test.models.PersonImplTests.initializeTestPerson;
import static org.junit.jupiter.api.Assertions.*;

public class TeamImplTests {
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
        assertThrows(IllegalArgumentException.class, () -> new TeamImpl("aaa"));
    }

    @Test
    public void construct_Should_CreateTeam_When_NameIsValid() {
        // Arrange, Act, Assert
        assertDoesNotThrow(() -> new TeamImpl(TaskBaseConstants.VALID_TEAM_NAME));
    }

    @Test
    public void construct_Should_InitializeNewListOfPerson_When_TeamIsCreated() {
        // Arrange, Act
        Team team = initializeTestTeam();

        // Assert
        assertNotNull(team.getPeople());
    }

    @Test
    public void constructor_Should_InitializeNewListOfBoard_When_TeamIsCreated() {
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
        Person personToAdd = initializeTestPerson();

       //  Act, Assert
        assertAll(
                () -> assertDoesNotThrow(() -> team.addPerson(personToAdd)),
                () -> assertEquals(1, team.getPeople().size())
        );
    }

    @Test
    public void add_Should_AddBoard_When_BoardIsValid() {
        // Arrange
        Team team = initializeTestTeam();
          Board boardToAdd = initializeTestBoard();

        //Act, Assert
        assertAll(
                () -> assertDoesNotThrow(() -> team.addBoard(boardToAdd)),
                () -> assertEquals(1, team.getBoards().size())
        );
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

    @Test
    public void getAsString_Should_printTeam() {
        // Arrange, Act
        Team team = initializeTestTeam();

        // Assert
        Assertions.assertEquals("Team name: xxxxxx", team.getAsString());
    }

    public static TeamImpl initializeTestTeam() {
        return new TeamImpl(TaskBaseConstants.VALID_TEAM_NAME);
    }
}
