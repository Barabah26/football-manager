package com.example.footballmanager.service.impl;

import com.example.footballmanager.dto.TeamDTO;
import com.example.footballmanager.entity.Team;
import com.example.footballmanager.exception.RecourseNotFoundException;
import com.example.footballmanager.exception.InvalidDataException;
import com.example.footballmanager.mapper.TeamMapper;
import com.example.footballmanager.repository.TeamRepository;
import com.example.footballmanager.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    public List<Team> getAllTeams() {
        try {
            return teamRepository.findAll();
        } catch (Exception e) {
            throw new InvalidDataException("Error retrieving teams: " + e.getMessage());
        }
    }

    @Transactional
    public TeamDTO createTeam(TeamDTO dto) {
        try {
            if (dto.getName() == null || dto.getName().isEmpty()) {
                throw new InvalidDataException("Team name cannot be empty");
            }
            Team team = teamRepository.save(TeamMapper.toEntity(dto));
            return TeamMapper.toDto(team);
        } catch (InvalidDataException e) {
            throw new InvalidDataException("Invalid data for team creation: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error creating team: " + e.getMessage());
        }
    }

    public TeamDTO updateTeam(Long id, TeamDTO dto) {
        try {
            Team team = teamRepository.findById(id)
                    .orElseThrow(() -> new RecourseNotFoundException("Team not found"));

            if (dto.getName() == null || dto.getName().isEmpty()) {
                throw new InvalidDataException("Team name cannot be empty");
            }

            team.setName(dto.getName());
            team.setBalance(dto.getBalance());
            team.setCommissionPercentage(dto.getCommissionPercentage());
            teamRepository.save(team);
            return TeamMapper.toDto(team);
        } catch (RecourseNotFoundException e) {
            throw new RecourseNotFoundException("Team not found with ID: " + id);
        } catch (InvalidDataException e) {
            throw new InvalidDataException("Invalid data for team update: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error updating team: " + e.getMessage());
        }
    }

    public void deleteTeam(Long id) {
        try {
            if (!teamRepository.existsById(id)) {
                throw new RecourseNotFoundException("Team not found with ID: " + id);
            }
            teamRepository.deleteById(id);
        } catch (RecourseNotFoundException e) {
            throw new RecourseNotFoundException("Team not found with ID: " + id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting team: " + e.getMessage());
        }
    }
}
