package com.management.oop.project.models.contracts;

import java.util.List;

public interface Person extends Team{
    String getName();

    Person getPerson();
    List<EventLog>getHistory();
}
