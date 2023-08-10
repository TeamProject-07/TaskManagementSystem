package com.management.oop.project.models.contracts;

import java.util.List;

public interface Team {

    String getName();
    List<Person>getPeople();
    List<EventLog> getHistory();

    List<Board> getBoards();
    void addBoard(Board board);
}
