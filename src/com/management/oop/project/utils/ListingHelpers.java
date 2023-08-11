package com.management.oop.project.utils;

import com.management.oop.project.models.contracts.Printable;

import java.util.ArrayList;
import java.util.List;

public class ListingHelpers {
    public static final String JOIN_DELIMITER = "####################";

    public static <T extends Printable> String getAsString(List<T> elements) {
        List<String> result= new ArrayList<>();
        for (T element : elements) {
            result.add(element.getAsString());
        }
        return String.join(JOIN_DELIMITER + System.lineSeparator(), result).trim();
    }
}
