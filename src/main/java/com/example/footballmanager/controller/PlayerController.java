package com.example.footballmanager.controller;

import com.example.footballmanager.dto.PlayerDTO;
import com.example.footballmanager.dto.TransferDTO;
import com.example.footballmanager.entity.Player;
import com.example.footballmanager.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @Operation(summary = "Get all players", description = "Retrieve the list of all players.")
    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @Operation(summary = "Create a new player", description = "Create a new player and save to the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Player created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public PlayerDTO createPlayer(@RequestBody @Valid PlayerDTO dto) {
        return playerService.createPlayer(dto);
    }

    @Operation(summary = "Update player details", description = "Update the details of an existing player.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Player updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Player not found")
    })
    @PutMapping("/{id}")
    public PlayerDTO updatePlayer(@Parameter(description = "ID of the player to be updated") @PathVariable Long id,
                                  @RequestBody @Valid PlayerDTO dto) {
        return playerService.updatePlayer(id, dto);
    }

    @Operation(summary = "Delete player", description = "Delete a player from the system by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Player deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Player not found")
    })
    @DeleteMapping("/{id}")
    public void deletePlayer(@Parameter(description = "ID of the player to be deleted") @PathVariable Long id) {
        playerService.deletePlayer(id);
    }

    @Operation(summary = "Transfer player", description = "Transfer a player to a different team.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Player transferred successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid transfer data"),
            @ApiResponse(responseCode = "404", description = "Player or team not found")
    })
    @PostMapping("/transfer")
    public void transferPlayer(@RequestBody @Valid TransferDTO transferDto) {
        playerService.transferPlayer(transferDto);
    }
}
