package com.management.oop.test.models;

import com.management.oop.project.models.EventLogImpl;
import com.management.oop.project.models.contracts.EventLog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventLogImplTests {
    @Test
    public void eventLogImpl_Should_ImplementEventLogInterface() {
        //Arrange, act
        EventLogImpl eventLog = initializeTestEventLog();
        //Assert
        Assertions.assertTrue(eventLog instanceof EventLog);
    }
    @Test
    public void constructor_Should_Throw_Exception_WhenDescriptionIsEmpty() {
        // Arrange, Act, Assert
        assertThrows(IllegalArgumentException.class,
                () -> new EventLogImpl(""));
    }
    public static EventLogImpl initializeTestEventLog() {
        return new EventLogImpl(
                "Description about Event Log.");
    }
}
