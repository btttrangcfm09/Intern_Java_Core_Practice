package com.example.demo.dto;

import java.time.LocalDate;
import java.util.Set;

import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record NewFilmRequest(@NotBlank String name, 
                             @NotBlank @Size(max = 200) String description,
                             @NotNull LocalDate premiereDate,
                             @NotNull @Positive int duration,
                             @NotNull Set<Long> genreIds,
                             @NotNull Set<Long> actorIds,
                             @NotNull Long mpaaId) {
} 

