package com.example.demo.dto;
import lombok.*;
@Builder
public record PostDto(Long id, String text, Long userId) {
}

