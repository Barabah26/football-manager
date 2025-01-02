package com.example.footballmanager.mapper;

import com.example.footballmanager.dto.TeamDTO;
import com.example.footballmanager.entity.Team;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {
    public static TeamDTO toDto(Team team) {
        return new TeamDTO(team.getName(), team.getBalance(), team.getCommissionPercentage());
    }

    public static Team toEntity(TeamDTO dto) {
        Team team = new Team();
        team.setName(dto.getName());
        team.setBalance(dto.getBalance());
        team.setCommissionPercentage(dto.getCommissionPercentage());
        return team;
    }
}
