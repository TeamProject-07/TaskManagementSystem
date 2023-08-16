package com.management.oop.test.commands.create;
import com.management.oop.project.commands.create.CreateNewBug;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateNewBugTest {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 6;
    private CreateNewBug createNewBug;
    private TaskManagementSystemRepository repository;
    private List<String> params;


    @BeforeEach
    public void before() {
        params = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
        createNewBug = new CreateNewBug(repository);
    }

   @Test
   public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
       // Arrange
       List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);
       // Act, Assert
       assertThrows(IllegalArgumentException.class, () -> createNewBug.execute(params));
   }

 @Test
 public void execute_Should_CreateNewBug_When_ArgumentsAreValid() {
     // Arrange
     repository.createTeam("teamName");
     repository.createBoard("boardName","teamName");
     params.add("boardName");
  params.add("bugValidName");
  params.add("validDescription");
  params.add("step1");
  params.add("HIGH");
  params.add("CRITICAL");
     // Act, Assert
     Assertions.assertDoesNotThrow(() ->  createNewBug.execute(params));
 }

   @Test
   public void execute_Should_ThrowException_When_InvalidStatus() {
       // Arrange
       repository.createTeam("teamName");
       repository.createBoard("boardName","teamName");
       params.add("boardName");
       params.add("bugValidName");
       params.add("validDescription");
       params.add("step1");
       params.add("HIGH");
       params.add("Invalid");


       // Act, Assert
       assertThrows(IllegalArgumentException.class, () -> createNewBug.execute(params));
   }


}
