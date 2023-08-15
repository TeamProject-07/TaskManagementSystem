package com.management.oop.test.commands.show;
import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.commands.show.ShowAllTeamBoardsCommand;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.BoardImpl;
import com.management.oop.project.models.TeamImpl;
import com.management.oop.project.models.contracts.Board;
import com.management.oop.project.models.contracts.Team;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShowAllTeamBoardsCommandTest{

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private TaskManagementSystemRepository repository;
    private ShowAllTeamBoardsCommand showAllTeamBoardsCommand;


    @BeforeEach
    public void before() {
        repository = new TaskManagementSystemRepositoryImpl();
        showAllTeamBoardsCommand = new ShowAllTeamBoardsCommand(repository);
    }

  @Test
  public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
      // Arrange
      List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

      // Act, Assert
      assertThrows(IllegalArgumentException.class, () -> showAllTeamBoardsCommand.execute(params));
  }

   @Test
  public void execute_Should_ThrowException_When_TeamNotExist() {
        List<String> params = new ArrayList<>();
   }

  //      params.add("InvalidTeamName");

  //      Assertions.assertThrows(IllegalArgumentException.class, () -> showAllTeamBoardsCommand.execute(params));
  //  }

  @Test
  public void should_ThrowException_When_teamDoesNotExist() {
      List<String> params = List.of(
              "invalidTeamName");
      // Act, Assert
      Assertions.assertThrows(IllegalArgumentException.class, () -> showAllTeamBoardsCommand.execute(params));
  }


 @Test
 public void should_ShowTeamBoards_When_ArgumentsAreValid() {
     // Arrange
     Team team = new TeamImpl("teamName");
     Board board = new BoardImpl("boardName");
     team.addBoard(board);

     // Act, Assert
     Assertions.assertEquals(1,team.getBoards().size());
 }

 //  @Test
 //  public void should_ShowTeamBoard_When_ArgumentsAreValid() {
 //      // Arrange
 //      Team team = new TeamImpl("teamName");
 //      Board board = new BoardImpl("boardName");
 //      team.addBoard(board);

 //      // Act, Assert
 //      Assertions.assertEquals(getBoardAsString("teamName"),String.valueOf(team.getBoards()));
 //  }

//  @Test
//  public void should_ShowCategory_When_ArgumentsAreValid() {
//      // Arrange
//      Team team = new TeamImpl("teamName");
//      Board board = new BoardImpl("boardName");
//      team.addBoard(board);
//      List<String> params = new ArrayList<>();
//      params.add(team.getName());

//      // Act, Assert

//      Assertions.assertEquals(1,team.getBoards().size());
//  }



 //  @Override
 //  public String execute(List<String> parameters) {
 //      ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
 //      String teamName = parameters.get(0);
 //      return ListingHelpers.boardsToString(repository.findTeamByName(teamName).getPeople());
 //  }


}
