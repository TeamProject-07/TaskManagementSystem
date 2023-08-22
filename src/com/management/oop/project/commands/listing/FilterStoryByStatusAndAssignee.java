package com.management.oop.project.commands.listing;

import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.contracts.Assignable;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.enums.StoryStatusEnum;
import com.management.oop.project.utils.ListingHelpers;
import com.management.oop.project.utils.ParsingHelpers;
import com.management.oop.project.utils.ValidationHelpers;

import java.util.List;
import java.util.stream.Collectors;

public class FilterStoryByStatusAndAssignee implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public FilterStoryByStatusAndAssignee(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }


    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String personName = parameters.get(0);
        Person person = taskManagementSystemRepository.findPersonByName(personName);
        StoryStatusEnum statusEnum = ParsingHelpers.tryParseEnum(parameters.get(1), StoryStatusEnum.class);
        return ListingHelpers.getAsString(filterStoryByAssigneeAndStatus(person, statusEnum));
    }

    private List<Assignable> filterStoryByAssigneeAndStatus(Person assignee, StoryStatusEnum statusEnum) {
        return taskManagementSystemRepository.getAllStories()
                .stream()
                .filter(story -> story.getAssignee() != null)
                .filter(story -> story.getAssignee().getName().equals(assignee.getName()))
                .filter(story -> story.getStoryStatusEnum().equals(statusEnum))
                .collect(Collectors.toList());
    }
}
