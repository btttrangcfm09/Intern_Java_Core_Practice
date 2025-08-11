package dev.cat.dto;

import lombok.Builder;

@Builder
public record NewEmployeeDto(String firstName,
                             String lastName) {
}