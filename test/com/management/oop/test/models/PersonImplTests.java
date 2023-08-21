package com.management.oop.test.models;

import com.management.oop.project.models.PersonImpl;
import com.management.oop.project.models.tasks.StoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PersonImplTests {
    public static final String INVALID_USERNAME ="aaa";
    public static final String VALID_USERNAME = "PersonName";

    @Test
    public void constructor_Should_CreateNewPerson_When_ParametersAreCorrect() {
        // Arrange, Act
        PersonImpl person = initializeTestPerson();

        // Assert
        Assertions.assertEquals(VALID_USERNAME, person.getName());
    }

    @Test
    public void getHistory_Should_ReturnCopyOfTheHistory() {
       // Arrange
        PersonImpl person = initializeTestPerson();
        //Act
        person.getHistory();

       // Assert
     Assertions.assertEquals(1, person.getHistory().size());
   }


    public static PersonImpl initializeTestPerson() {
        return new PersonImpl(
                VALID_USERNAME);
    }
}
