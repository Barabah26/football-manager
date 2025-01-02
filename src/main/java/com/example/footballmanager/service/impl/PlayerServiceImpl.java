package com.example.footballmanager.service.impl;

import com.example.footballmanager.dto.PlayerDTO;
import com.example.footballmanager.dto.TransferDTO;
import com.example.footballmanager.entity.Player;
import com.example.footballmanager.entity.Team;
import com.example.footballmanager.exception.InsufficientBalanceException;
import com.example.footballmanager.exception.RecourseNotFoundException;
import com.example.footballmanager.exception.InvalidDataException;
import com.example.footballmanager.mapper.PlayerMapper;
import com.example.footballmanager.repository.PlayerRepository;
import com.example.footballmanager.repository.TeamRepository;
import com.example.footballmanager.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public List<Player> getAllPlayers() {
        try {
            return playerRepository.findAll();
        } catch (Exception e) {
            throw new InvalidDataException("Error retrieving players: " + e.getMessage());
        }
    }

    public PlayerDTO createPlayer(PlayerDTO dto) {
        try {
            Team team = teamRepository.findById(dto.getTeamId())
                    .orElseThrow(() -> new RecourseNotFoundException("Team not found"));
            Player player = playerRepository.save(PlayerMapper.toEntity(dto, team));
            return PlayerMapper.toDto(player);
        } catch (RecourseNotFoundException e) {
            throw new RecourseNotFoundException("Team not found for player creation: " + e.getMessage());
        } catch (Exception e) {
            throw new InvalidDataException("Invalid data for player creation: " + e.getMessage());
        }
    }

    public PlayerDTO updatePlayer(Long id, PlayerDTO dto) {
        try {
            Player player = playerRepository.findById(id)
                    .orElseThrow(() -> new RecourseNotFoundException("Player not found"));
            Team team = teamRepository.findById(dto.getTeamId())
                    .orElseThrow(() -> new RecourseNotFoundException("Team not found"));

            if (dto.getName() == null || dto.getName().isEmpty()) {
                throw new InvalidDataException("Player name cannot be empty");
            }

            player.setName(dto.getName());
            player.setExperienceMonths(dto.getExperienceMonths());
            player.setAge(dto.getAge());
            player.setTeam(team);
            playerRepository.save(player);
            return PlayerMapper.toDto(player);
        } catch (RecourseNotFoundException e) {
            throw new RecourseNotFoundException("Player or team not found for update: " + e.getMessage());
        } catch (InvalidDataException e) {
            throw new InvalidDataException("Invalid data for player update: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error updating player: " + e.getMessage());
        }
    }

    public void deletePlayer(Long id) {
        try {
            if (!playerRepository.existsById(id)) {
                throw new RecourseNotFoundException("Player not found with ID: " + id);
            }
            playerRepository.deleteById(id);
        } catch (RecourseNotFoundException e) {
            throw new RecourseNotFoundException("Player not found with ID: " + id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting player: " + e.getMessage());
        }
    }

    public void transferPlayer(TransferDTO transferDto) {
        try {
            Player player = playerRepository.findById(transferDto.getPlayerId())
                    .orElseThrow(() -> new RecourseNotFoundException("Player not found"));
            Team targetTeam = teamRepository.findById(transferDto.getTargetTeamId())
                    .orElseThrow(() -> new RecourseNotFoundException("Target team not found"));
            Team sourceTeam = player.getTeam();

            double transferValue = (player.getExperienceMonths() * 100_000.0) / player.getAge();
            double commission = transferValue * (sourceTeam.getCommissionPercentage() / 100.0);
            double totalCost = transferValue + commission;

            if (targetTeam.getBalance() < totalCost) {
                throw new InsufficientBalanceException("Not enough funds in target team for transfer");
            }

            targetTeam.setBalance(targetTeam.getBalance() - totalCost);
            sourceTeam.setBalance(sourceTeam.getBalance() + totalCost);
            player.setTeam(targetTeam);

            teamRepository.save(sourceTeam);
            teamRepository.save(targetTeam);
            playerRepository.save(player);
        } catch (RecourseNotFoundException e) {
            throw new RecourseNotFoundException("Player or team not found for transfer: " + e.getMessage());
        } catch (InsufficientBalanceException e) {
            throw new InsufficientBalanceException("Insufficient balance for transfer: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error during player transfer: " + e.getMessage());
        }
    }
}
