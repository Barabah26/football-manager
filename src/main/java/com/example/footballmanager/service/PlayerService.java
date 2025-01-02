package com.example.footballmanager.service;

import com.example.footballmanager.dto.PlayerDTO;
import com.example.footballmanager.dto.TransferDTO;
import com.example.footballmanager.entity.Player;

import java.util.List;

public interface PlayerService {
    List<Player> getAllPlayers();
    PlayerDTO createPlayer(PlayerDTO dto);
    PlayerDTO updatePlayer(Long id, PlayerDTO dto);
    void deletePlayer(Long id);
    void transferPlayer(TransferDTO transferDto);

}
