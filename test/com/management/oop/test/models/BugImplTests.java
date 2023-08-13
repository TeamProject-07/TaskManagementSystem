package com.management.oop.test.models;

import com.management.oop.project.models.CommentImpl;
import com.management.oop.project.models.contracts.Bug;
import com.management.oop.project.models.enums.BugSeverityEnum;
import com.management.oop.project.models.enums.BugStatusEnum;
import com.management.oop.project.models.enums.PriorityEnum;
import com.management.oop.project.models.tasks.BugImpl;
import com.management.oop.test.utils.TaskBaseConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

public class BugImplTests {
    @Test
    public void bugImpl_Should_ImplementBugInterface(){
        //Arrange, act
        BugImpl bug=initializeTestBug();
        //Assert
        Assertions.assertTrue(bug instanceof Bug);
    }
    @Test
    public void constructor_Should_Throw_Exception_WhenTitleInvalid(){
        //Arrange
        List<String>steps=new ArrayList<>();
        //Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new BugImpl(
                        1, TaskBaseConstants.INVALID_TITLE, TaskBaseConstants.VALID_DESCRIPTION, steps, PriorityEnum.HIGH, BugSeverityEnum.CRITICAL
                ));
    }
    @Test
    public void constructor_Should_Throw_Exception_WhenDescriptionInvalid(){
        //Arrange
        List<String>steps=new ArrayList<>();
        //Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new BugImpl(
                        1, TaskBaseConstants.VALID_TITLE, TaskBaseConstants.INVALID_DESCRIPTION, steps, PriorityEnum.HIGH, BugSeverityEnum.CRITICAL
                ));
    }
    @Test
    public void constructor_Should_CreateNewBug_When_Parameters_areValid(){
        //Arrange
        Bug bug=initializeTestBug();
        //Act, Assert
        Assertions.assertAll(
                () ->Assertions.assertEquals(TaskBaseConstants.VALID_TITLE, bug.getTitle()),
                () ->Assertions.assertEquals(TaskBaseConstants.VALID_DESCRIPTION, bug.getDescription())
        );
    }
    @Test
    public void changeStatus_Should_ChangeTheStatus_OfBug(){
        //Arrange
        Bug bug=initializeTestBug();
        //Act
        bug.changeStatus(BugStatusEnum.FIXED);
        //Assert
        Assertions.assertEquals(bug.getStatus(), BugStatusEnum.FIXED);
    }
    @Test
    public void changePriority_Should_ChangeThePriority_OfBug(){
        //Arrange
        Bug bug=initializeTestBug();
        //Act
        bug.changePriorityEnum(PriorityEnum.MEDIUM);
        //Assert
        Assertions.assertEquals(bug.getBugPriorityEnum(), PriorityEnum.MEDIUM);
    }
    @Test
    public void changeSeverity_Should_ChangeTheSeverity_OfBug(){
        //Arrange
        Bug bug=initializeTestBug();
        //Act
        bug.changeSeverityEnum(BugSeverityEnum.MAJOR);
        //Assert
        Assertions.assertEquals(bug.getBugSeverityEnum(), BugSeverityEnum.MAJOR);
    }
    public static BugImpl initializeTestBug(){
        List<String>steps=new ArrayList<>();
        return new BugImpl
                (1,
                        TaskBaseConstants.VALID_TITLE,
                        TaskBaseConstants.VALID_DESCRIPTION,
                        steps,
                        PriorityEnum.HIGH,
                        BugSeverityEnum.CRITICAL);
    }
}
