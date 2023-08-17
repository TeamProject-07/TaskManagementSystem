package com.management.oop.project.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Story;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortStory implements Command {

    TaskManagementSystemRepository taskManagementSystemRepository;
    private List<Story> stories;



    public SortStory(TaskManagementSystemRepository taskManagementSystemRepository, List<Story> stories) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
        this.stories = stories;
    }

    @Override
    public String execute(List<String> parameters) {
        return sortStory();
    }

    private String sortStory(){
        return stories
                .stream()
                .sorted(Comparator.comparing(Story :: getTitle).thenComparing(Story::getPriorityEnum))
                .map(story -> story.getTitle() + " " + story.getPriorityEnum())
                .collect(Collectors.toList()).toString();
    }
}
