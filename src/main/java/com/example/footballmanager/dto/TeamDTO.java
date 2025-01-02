package com.example.footballmanager.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class TeamDTO {
    @NotBlank
    private String name;

    @Min(0)
    private Double balance;

    @Min(0)
    @Max(10)
    private Double commissionPercentage;

}
