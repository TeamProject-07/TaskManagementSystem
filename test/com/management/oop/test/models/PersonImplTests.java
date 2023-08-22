package com.management.oop.test.models;

import com.management.oop.project.models.PersonImpl;
import com.management.oop.test.utils.TaskBaseConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PersonImplTests {

    @Test
    public void constructor_Should_CreateNewPerson_When_ParametersAreCorrect() {
        // Arrange, Act
        PersonImpl person = initializeTestPerson();

        // Assert
        Assertions.assertEquals(TaskBaseConstants.VALID_PERSON_NAME, person.getName());
    }

    @Test
    public void getHistory_Should_ReturnCopyOfTheHistory() {
        // Arrange
        PersonImpl person = initializeTestPerson();
        // Act
        person.getHistory();

        // Assert
        Assertions.assertEquals(1, person.getHistory().size());
    }


    public static PersonImpl initializeTestPerson() {
        return new PersonImpl(
                TaskBaseConstants.VALID_PERSON_NAME);
    }
}
