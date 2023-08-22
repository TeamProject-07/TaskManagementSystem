package com.management.oop.project.models.contracts;

import java.util.List;

public interface Team extends Printable {

    String getName();

    List<Person> getPeople();

    List<EventLog> getHistory();

    void addPerson(Person person);

    List<Board> getBoards();

    void addBoard(Board board);
}
