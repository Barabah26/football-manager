package com.example.footballmanager.mapper;

import com.example.footballmanager.dto.PlayerDTO;
import com.example.footballmanager.entity.Player;
import com.example.footballmanager.entity.Team;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {
    public static PlayerDTO toDto(Player player) {
        return new PlayerDTO(player.getName(), player.getExperienceMonths(),
                player.getAge(), player.getTeam().getId());
    }

    public static Player toEntity(PlayerDTO dto, Team team) {
        Player player = new Player();
        player.setName(dto.getName());
        player.setExperienceMonths(dto.getExperienceMonths());
        player.setAge(dto.getAge());
        player.setTeam(team);
        return player;
    }
}

