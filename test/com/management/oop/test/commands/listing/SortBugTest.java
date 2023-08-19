package com.management.oop.test.commands.listing;

import com.management.oop.project.commands.listing.FilterStoryByStatus;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SortBugTest {
    private List<String> parameters;
    private TaskManagementSystemRepository repository;
    private SortBugTest sortBugTest;

    @BeforeEach
    public void before(){
        parameters = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
    }


}
