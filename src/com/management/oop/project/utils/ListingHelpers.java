package com.management.oop.project.utils;

import com.management.oop.project.models.contracts.Board;
import com.management.oop.project.models.contracts.Person;
import com.management.oop.project.models.contracts.Printable;

import java.util.ArrayList;
import java.util.List;

public class ListingHelpers {
    public static String membersToString(List<Person> people) {
        List<String> result = new ArrayList<>();
        for (Person person : people) {
            result.add(person.getAsString());
        }

        return String.join(System.lineSeparator(), result).trim();
    }

    public static String boardsToString(List<Board> boards) {
        List<String> result = new ArrayList<>();
        for (Board board : boards) {
            result.add(board.getAsString());
        }

        return String.join(System.lineSeparator(), result).trim();
    }

    public static <T extends Printable> String getAsString(List<T> elements) {
        List<String> result = new ArrayList<>();
        for (T element : elements) {
            result.add(element.getAsString());
        }
        return String.join(System.lineSeparator(), result).trim();
    }
}
