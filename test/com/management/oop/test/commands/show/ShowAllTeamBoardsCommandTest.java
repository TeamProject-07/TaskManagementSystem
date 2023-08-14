package com.management.oop.test.commands.show;

import com.management.oop.project.commands.change.ChangeFeedbackCommand;
import com.management.oop.project.commands.contracts.Command;
import com.management.oop.project.commands.create.CreatePersonCommand;
import com.management.oop.project.commands.show.ShowAllTeamBoardsCommand;
import com.management.oop.project.core.TaskManagementSystemRepositoryImpl;
import com.management.oop.project.core.contracts.TaskManagementSystemRepository;
import com.management.oop.project.models.BoardImpl;
import com.management.oop.project.models.TeamImpl;
import com.management.oop.project.models.contracts.Board;
import com.management.oop.project.models.contracts.Team;
import com.management.oop.project.utils.ListingHelpers;
import com.management.oop.project.utils.ValidationHelpers;
import com.management.oop.test.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShowAllTeamBoardsCommandTest{

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private Command command;
    private TaskManagementSystemRepository repository;

    private ShowAllTeamBoardsCommand showAllTeamBoardsCommand;

    @BeforeEach
    public void before() {
        this.repository = new TaskManagementSystemRepositoryImpl();
        this.command = new CreatePersonCommand(repository);
    }

  @Test
  public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
      // Arrange
      List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

      // Act, Assert
      assertThrows(IllegalArgumentException.class, () -> command.execute(params));
  }

//  @Test
//  public void should_ThrowException_When_teamDoesNotExist() {
//      Team team = new TeamImpl("teamName");
//      List<String> params = List.of(
//              "invalidTeamName");
//      // Act, Assert
//      Assertions.assertThrows(IllegalArgumentException.class, () -> showAllTeamBoardsCommand.execute(params));
//  }


//  @Test
//  public void should_ShowTeamBoards_When_ArgumentsAreValid() {
//      // Arrange
//      Team team = new TeamImpl("teamName");
//      Board board = new BoardImpl("boardName");
//      team.addBoard(board);

//      // Act, Assert
//      Assertions.assertEquals(1,team.getBoards().size());
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
//      return null;
//  }


 //  @Override
 //  public String execute(List<String> parameters) {
 //      ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
 //      String teamName = parameters.get(0);
 //      return ListingHelpers.boardsToString(repository.findTeamByName(teamName).getPeople());
 //  }


 //  private String getMemberAsString(String teamName) {
 //      Team team = taskManagementSystemRepository.findTeamByName(teamName);
 //      return String.valueOf(team.getPeople());
 //  }
}
