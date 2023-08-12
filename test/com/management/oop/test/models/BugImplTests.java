package com.management.oop.test.models;

import com.management.oop.project.models.contracts.Bug;
import com.management.oop.project.models.enums.BugSeverityEnum;
import com.management.oop.project.models.enums.PriorityEnum;
import com.management.oop.project.models.tasks.BugImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
    public static BugImpl initializeTestBug(){
        List<String>steps=new ArrayList<>();
        return new BugImpl
                (1, "title", "description", steps, PriorityEnum.HIGH, BugSeverityEnum.CRITICAL);
    }
}
