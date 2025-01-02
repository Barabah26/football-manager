package com.example.footballmanager.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransferDTO {
    @NotNull(message = "playerId не може бути відсутнім, має бути задано")
    private Long playerId;

    @NotNull(message = "targetTeamId не може бути відсутнім, має бути задано")
    private Long targetTeamId;
}

