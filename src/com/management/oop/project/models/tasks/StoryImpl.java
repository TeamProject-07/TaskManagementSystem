package com.management.oop.project.models.tasks;

import com.management.oop.project.models.EventLogImpl;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.contracts.Story;
import com.management.oop.project.models.enums.PriorityEnum;
import com.management.oop.project.models.enums.StorySizeEnum;
import com.management.oop.project.models.enums.StoryStatusEnum;

public class StoryImpl extends TaskBase implements Story {
    private PriorityEnum priorityEnum;
    private StorySizeEnum storySizeEnum;
    private StoryStatusEnum storyStatusEnum;
    private Person assignee;

    public StoryImpl(int id, String title, String description, PriorityEnum priorityEnum,
                     StorySizeEnum storySizeEnum, StoryStatusEnum storyStatusEnum) {
        super(id, title, description);
        this.priorityEnum = priorityEnum;
        this.storySizeEnum = storySizeEnum;
        this.storyStatusEnum = storyStatusEnum;
        addHistory(new EventLogImpl("Story was created"));
    }

    public PriorityEnum getPriorityEnum() {
        return priorityEnum;
    }

    public StorySizeEnum getStorySizeEnum() {
        return storySizeEnum;
    }

    public StoryStatusEnum getStoryStatusEnum() {
        return storyStatusEnum;
    }

    public Person getAssignee() {
        return assignee;
    }

    private void setAssignee(Person assignee) {
        this.assignee = assignee;
    }

    @Override
    public void changePriorityEnum(PriorityEnum priorityEnum) {
        this.priorityEnum = priorityEnum;
        addHistory(new EventLogImpl("Priority was changed"));

    }

    @Override
    public void changeSize(StorySizeEnum storySizeEnum) {
        this.storySizeEnum = storySizeEnum;
        addHistory(new EventLogImpl("Size was changed"));

    }

    @Override
    public void changeStoryStatusEnum(StoryStatusEnum storyStatusEnum) {
        this.storyStatusEnum = storyStatusEnum;
        addHistory(new EventLogImpl("Status was changed"));
    }

}
