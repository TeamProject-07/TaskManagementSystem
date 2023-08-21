package com.management.oop.project.models.tasks;

import com.management.oop.project.models.EventLogImpl;
import com.management.oop.project.models.contracts.Assignable;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.contracts.Story;
import com.management.oop.project.models.enums.PriorityEnum;
import com.management.oop.project.models.enums.StorySizeEnum;
import com.management.oop.project.models.enums.StoryStatusEnum;

public class StoryImpl extends TaskBase implements Story, Assignable {
    public static final String STATUS_WAS_CHANGED = "Status was changed";
    public static final String SIZE_WAS_CHANGED = "Size was changed";
    public static final String PRIORITY_WAS_CHANGED = "Priority was changed";
    public static final String STORY_CREATED = "Story was created";
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
        addHistory(new EventLogImpl(STORY_CREATED));
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

    @Override
    public Person getAssignee() {
        return assignee;
    }

    @Override
    public void assignTask(Person assignee) {
        this.assignee=assignee;
    }
    @Override
    public void unAssignTask(Person assignee) {
        this.assignee = assignee;
    }

    @Override
    public void changePriorityEnum(PriorityEnum priorityEnum) {
        this.priorityEnum = priorityEnum;
        addHistory(new EventLogImpl(PRIORITY_WAS_CHANGED));

    }

    @Override
    public void changeSize(StorySizeEnum storySizeEnum) {
        this.storySizeEnum = storySizeEnum;
        addHistory(new EventLogImpl(SIZE_WAS_CHANGED));

    }

    @Override
    public void changeStoryStatusEnum(StoryStatusEnum storyStatusEnum) {
        this.storyStatusEnum = storyStatusEnum;
        addHistory(new EventLogImpl(STATUS_WAS_CHANGED));
    }

    @Override
    public String getAsString() {
        return String.format("""
                %s
                Priority: %s
                Size: %s
                Status: %s
                """, super.getAsString(),
                getPriorityEnum(),
                getStorySizeEnum(),
                getStoryStatusEnum());
    }
}
