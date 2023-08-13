package com.management.oop.test.commands.add;

import com.management.oop.project.commands.add.AddPersonToTeamCommand;
import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.contracts.Team;
import com.management.oop.test.models.PersonImplTests;
import com.management.oop.test.models.TeamImplTests;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AddPersonToTeamCommandTest {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private List<String> parameters;
    private TaskManagementSystemRepository repository;
    private Person person;
    private Team team;
    private Command addPersonToTeam;

    @BeforeEach
    public void before() {
        parameters = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
        addPersonToTeam = new AddPersonToTeamCommand(repository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> addPersonToTeam.execute(params));
    }

//    @Test
//    public void should_Create_When_InputIsValid() {
//        // Arrange
//        team.addPerson(PersonImplTests.initializeTestPerson());
//
//        List<String> params = List.of(
//                person.getName(),
//                team.getName());
//
//        // Act
//        addPersonToTeam.execute(params);
//
//        // Assert
//        List<Person> teamMembers = repository.findTeamByName(team.getName()).getPeople();
//        Assertions.assertEquals(1, teamMembers.size());
//        Assertions.assertEquals(person.getName(), teamMembers.get(0).getName());
//    }

    @Test
    public void execute_Should_AddNewPersonToTeam_When_ValidParameters() {

        repository.createPerson(PersonImplTests.VALID_USERNAME);
        repository.createTeam(TeamImplTests.VALID_TEAM_NAME);

        parameters.add(PersonImplTests.VALID_USERNAME);
        parameters.add(TeamImplTests.VALID_TEAM_NAME);

        addPersonToTeam.execute(parameters);

        Person person = repository.findPersonByName(PersonImplTests.VALID_USERNAME);
        Team team = repository.findTeamByName(TeamImplTests.VALID_TEAM_NAME);
        Assertions.assertTrue(team.getPeople().contains(person));
    }

    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        parameters = TestUtilities.getList(1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> addPersonToTeam.execute(parameters));
    }

    @Test
    public void execute_Should_ThrowException_When_PersonNotExist() {
        repository.createPerson(PersonImplTests.VALID_USERNAME);
        repository.createTeam(TeamImplTests.VALID_TEAM_NAME);

        parameters.add(PersonImplTests.VALID_USERNAME);
        parameters.add("aaa");

        Assertions.assertThrows(IllegalArgumentException.class, () -> addPersonToTeam.execute(parameters));
    }
    @Test
    public void execute_Should_ThrowException_When_TeamNotExist() {
        repository.createPerson(PersonImplTests.VALID_USERNAME);
        repository.createTeam(TeamImplTests.VALID_TEAM_NAME);

        parameters.add("aaa");
        parameters.add(TeamImplTests.VALID_TEAM_NAME);

        Assertions.assertThrows(IllegalArgumentException.class, () -> addPersonToTeam.execute(parameters));
    }
}
