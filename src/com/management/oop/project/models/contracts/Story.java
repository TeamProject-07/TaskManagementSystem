package com.management.oop.project.models.contracts;

import com.management.oop.project.models.enums.PriorityEnum;
import com.management.oop.project.models.enums.StorySizeEnum;
import com.management.oop.project.models.enums.StoryStatusEnum;

public interface Story extends Task{


    void changePriorityEnum(PriorityEnum priorityEnum);

    void changeSize(StorySizeEnum storySizeEnum);

    void changeStoryStatusEnum(StoryStatusEnum storyStatusEnum);
}
