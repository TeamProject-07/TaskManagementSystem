package com.management.oop.project.core.contracts;

import com.management.oop.project.models.contracts.Member;
import com.management.oop.project.models.contracts.Task;
import com.management.oop.project.models.contracts.Team;

import java.util.List;

public interface TaskManagementSystemRepository {

    List<Team> getTeams();

    List<Member> getMembers();

    List<Task> getTasks();

    Team findTeamById(int id);

    Member findMemberById(int id);

    Task findTaskById(int id);

    Member createMember();

}
