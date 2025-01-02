package com.example.footballmanager.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerDTO {
    @NotBlank
    private String name;

    @Min(1)
    private Integer experienceMonths;

    @Min(18)
    private Integer age;

    private Long teamId;
}

