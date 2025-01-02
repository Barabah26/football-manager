package com.example.footballmanager.controller;

import com.example.footballmanager.dto.TeamDTO;
import com.example.footballmanager.entity.Team;
import com.example.footballmanager.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @Operation(summary = "Get all teams", description = "Retrieve the list of all teams.")
    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @Operation(summary = "Create a new team", description = "Create a new team and save it in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Team created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public TeamDTO createTeam(@RequestBody @Valid TeamDTO dto) {
        return teamService.createTeam(dto);
    }

    @Operation(summary = "Update team details", description = "Update the details of an existing team.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Team updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Team not found")
    })
    @PutMapping("/{id}")
    public TeamDTO updateTeam(@Parameter(description = "ID of the team to be updated") @PathVariable Long id,
                              @RequestBody @Valid TeamDTO dto) {
        return teamService.updateTeam(id, dto);
    }

    @Operation(summary = "Delete team", description = "Delete a team from the system by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Team deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Team not found")
    })
    @DeleteMapping("/{id}")
    public void deleteTeam(@Parameter(description = "ID of the team to be deleted") @PathVariable Long id) {
        teamService.deleteTeam(id);
    }
}
