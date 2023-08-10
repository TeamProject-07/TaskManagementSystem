package com.management.oop.project.commands.add;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Person;

import java.util.List;

public class AddCommentToATask implements Command {
    private TaskManagementSystemRepository taskManagementSystemRepository;

    public AddCommentToATask(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        return null;
    }

    private void addComment(String message, String author, int taskId){
        Person person=taskManagementSystemRepository.findPersonByName(author);

    }
}
