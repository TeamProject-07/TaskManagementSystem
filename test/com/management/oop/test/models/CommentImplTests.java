package com.management.oop.test.models;

import com.management.oop.project.models.CommentImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommentImplTests {
    @Test
    public void constructor_Should_CreateNewComment_When_ParametersAreCorrect() {
        // Arrange, Act
        CommentImpl comment = initializeTestComment();

        // Assert
        Assertions.assertEquals("message", comment.getMessage());
        Assertions.assertEquals("author", comment.getAuthor());
    }

    public static CommentImpl initializeTestComment() {
        return new CommentImpl(
                "message",
                "author");
    }
}
