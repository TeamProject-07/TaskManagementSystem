package com.management.oop.project.commands.add;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.CommentImpl;
import com.management.oop.project.models.contracts.Comment;
import com.management.oop.project.models.contracts.Task;
import com.management.oop.project.utils.ParsingHelpers;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;

public class AddCommentToATask implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    public static final String COMMENT_ADDED = "Comment added to task with ID %d";
    public static final String THERE_IS_DOES_NOT_TASK = "There is no task with this id %d";
    private TaskManagementSystemRepository taskManagementSystemRepository;

    public AddCommentToATask(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String message = parameters.get(0);
        String author = parameters.get(1);
        int id = ParsingHelpers.tryParseInteger(parameters.get(2), "id");
        addComment(message, author, id);
        return addComment(message, author, id);
    }

    private String addComment(String message, String author, int taskId) {
        if (taskManagementSystemRepository.taskExist(taskId)) {
            Comment commentToAdd = new CommentImpl(message, author);
            Task task = taskManagementSystemRepository.findTaskById(taskId);
            task.addComment(commentToAdd);
            return String.format(COMMENT_ADDED, taskId);
        }
        throw new IllegalArgumentException(String.format(THERE_IS_DOES_NOT_TASK, taskId));
    }
}
