package com.management.oop.test.commands.show;

import com.management.oop.project.commands.show.ShowAllPeopleCommand;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.models.PersonImpl;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.utils.ListingHelpers;
import com.management.oop.test.models.PersonImplTests;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowAllPeopleCommandTest {
    private List<String> parameters;
    private TaskManagementSystemRepositoryImpl repository;
    private ShowAllPeopleCommand showAllPeopleCommand;
    @BeforeEach
    public void setupTest() {
        parameters = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
        showAllPeopleCommand = new ShowAllPeopleCommand(repository);
    }
//    @Test
//    public void execute_Should_ReturnOutput_When_ValidParameters() {
//        Person person = new PersonImpl(PersonImplTests.VALID_USERNAME);
//        repository.createPerson(person.getName());
//
//        Assertions.assertEquals(ListingHelpers.getAsString(repository.getPeople()),
//                showAllPeopleCommand.execute(parameters));
//    }
//
//    @Test
//    public void execute_Should_ThrowException_When_PeopleNotExist() {
//        parameters.add("aaa");
//
//        Assertions.assertThrows(IllegalArgumentException.class, () -> showAllPeopleCommand.execute(parameters));
//    }
}
