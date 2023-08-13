package com.management.oop.test.models;

import com.management.oop.project.models.EventLogImpl;
import com.management.oop.project.models.PersonImpl;
import com.management.oop.project.models.tasks.StoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PersonImplTests {
    public static final int USERNAME_LEN_MIN = 2;
    private static final String VALID_USERNAME = "PersonName";

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

    @Test
    public void personActivity_Should_beAddedToTheHistory() {
        // Arrange
        PersonImpl person = initializeTestPerson();
        StoryImpl story = StoryImplTests.initializeTestStory();
        person.assignTask(story);
        //Act
        person.getHistory();
        // Assert
        Assertions.assertEquals(2, person.getHistory().size());
    }

    @Test
    public void getTasks_Should_ReturnCopyOfTheTasks() {
        // Arrange
        PersonImpl person = initializeTestPerson();
        //Act
        person.getTasks();

        // Assert
        Assertions.assertEquals(0, person.getTasks().size());
    }


   @Test
   public void assignTask_Should_AddTaskToTheCollection() {
       // Arrange
       PersonImpl person = initializeTestPerson();
       StoryImpl story = StoryImplTests.initializeTestStory();
       person.assignTask(story);
       // Act
       person.getTasks();
       // Assert
       Assertions.assertEquals(1, person.getTasks().size());
   }

    @Test
    public void unassignTask_Should_RemoveTaskFromTheCollection() {
        // Arrange
        PersonImpl person = initializeTestPerson();
        StoryImpl story = StoryImplTests.initializeTestStory();
        person.assignTask(story);
        person.unAssignTask(story);
        // Act
        person.getTasks();
        // Assert
        Assertions.assertEquals(0, person.getTasks().size());
    }

    public static PersonImpl initializeTestPerson() {
        return new PersonImpl(
                VALID_USERNAME);
    }
}
