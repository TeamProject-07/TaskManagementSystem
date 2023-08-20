package com.management.oop.test.models;

import com.management.oop.project.models.BoardImpl;
import com.management.oop.project.models.contracts.Board;
import com.management.oop.project.models.contracts.Bug;
import com.management.oop.project.models.contracts.Feedback;
import com.management.oop.project.models.contracts.Story;
import com.management.oop.project.models.enums.*;
import com.management.oop.project.models.tasks.BugImpl;
import com.management.oop.project.models.tasks.FeedbackImpl;
import com.management.oop.project.models.tasks.StoryImpl;
import com.management.oop.test.utils.TaskBaseConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class BoardImplTests {
    @Test
    public void bugImpl_Should_ImplementBugInterface() {
        //Arrange, act
        BoardImpl board = initializeTestBoard();
        //Assert
        Assertions.assertTrue(board instanceof Board);
    }

    @Test
    public void constructor_Should_Throw_Exception_WhenNameInvalid() {
        //Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new BoardImpl(TaskBaseConstants.INVALID_BOARD_NAME));
    }

    @Test
    public void constructor_Should_CreateNewBoard_When_Parameters_areValid() {
        //Arrange
        Board board = initializeTestBoard();
        //Act, Assert
        Assertions.assertEquals(TaskBaseConstants.VALID_BOARD_NAME, board.getName());
    }

    @Test
    public void addBug_Should_AddNewBug_ToBoard() {
        //Arrange
        Board board = initializeTestBoard();
        Bug bug = initializeTestBug();
        //Act
        board.addBug(bug);
        //Assert
        Assertions.assertEquals(1, board.getBugs().size());
    }

    @Test
    public void addStory_Should_AddNewBug_ToBoard() {
        //Arrange
        Board board = initializeTestBoard();
        Story story = initializeTestStory();
        //Act
        board.addStory(story);
        //Assert
        Assertions.assertEquals(1, board.getStories().size());
    }

    @Test
    public void addFeedback_Should_AddNewBug_ToBoard() {
        //Arrange
        Board board = initializeTestBoard();
        Feedback feedback = initializeTestFeedback();
        //Act
        board.addFeedback(feedback);
        //Assert
        Assertions.assertEquals(1, board.getFeedbacks().size());
    }

    @Test
    public void getBugs_Should_ReturnCopyOfTheCollection() {
        // Arrange
        Board board=initializeTestBoard();
        Bug bug = initializeTestBug();
        // Act
        board.getBugs().add(bug);
        // Assert
        Assertions.assertEquals(0, board.getBugs().size());
    }
    @Test
    public void getStories_Should_ReturnCopyOfTheCollection() {
        // Arrange
        Board board=initializeTestBoard();
        Story story=initializeTestStory();
        // Act
        board.getStories().add(story);
        // Assert
        Assertions.assertEquals(0, board.getStories().size());
    }
    @Test
    public void getFeedbacks_Should_ReturnCopyOfTheCollection() {
        // Arrange
        Board board=initializeTestBoard();
        Feedback feedback=initializeTestFeedback();
        // Act
        board.getFeedbacks().add(feedback);
        // Assert
        Assertions.assertEquals(0, board.getFeedbacks().size());
    }
    @Test
    public void getTasks_Should_ReturnCopyOfTheCollection() {
        // Arrange
        Board board=initializeTestBoard();
        Feedback feedback=initializeTestFeedback();
        // Act
        board.getTasks().add(feedback);
        // Assert
        Assertions.assertEquals(0, board.getTasks().size());
    }
    public static BoardImpl initializeTestBoard() {
        return new BoardImpl(TaskBaseConstants.VALID_BOARD_NAME);
    }

    public static BugImpl initializeTestBug() {
        //List<String> steps = new ArrayList<>();
        return new BugImpl(
                    1,
                        TaskBaseConstants.VALID_TITLE,
                        TaskBaseConstants.VALID_DESCRIPTION,
                        TaskBaseConstants.STEPS,
                        PriorityEnum.HIGH,
                        BugSeverityEnum.CRITICAL);
    }

    public static StoryImpl initializeTestStory() {
        return new StoryImpl(
                1,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                PriorityEnum.HIGH,
                StorySizeEnum.LARGE,
                StoryStatusEnum.IN_PROGRESS);
    }

    public static FeedbackImpl initializeTestFeedback() {
        return new FeedbackImpl(
                1,
                TaskBaseConstants.VALID_TITLE,
                TaskBaseConstants.VALID_DESCRIPTION,
                TaskBaseConstants.VALID_RATING,
                FeedbackStatusEnum.DONE);
    }
}
