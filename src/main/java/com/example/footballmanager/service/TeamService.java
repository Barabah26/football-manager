package com.example.footballmanager.service;

import com.example.footballmanager.dto.TeamDTO;
import com.example.footballmanager.entity.Team;

import java.util.List;

public interface TeamService {
    List<Team> getAllTeams();
    TeamDTO createTeam(TeamDTO dto);
    TeamDTO updateTeam(Long id, TeamDTO dto);
    void deleteTeam(Long id);
}
