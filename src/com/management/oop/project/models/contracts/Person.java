package com.management.oop.project.models.contracts;

import java.util.List;

public interface Person extends Printable {
    String getName();

    List<EventLog> getHistory();
}
