package com.management.oop.project.models.contracts;

import java.util.List;

public interface Person{
    String getName();
    List<EventLog>getHistory();
}
