package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;

@Builder
public record FilmDto(String name,String description, LocalDate premiereDate,
                      int duration, List<String> genres, String mpaa, List<String> actors, double rating) {
}
// instead of returning Sets of nested entities (Genre, FilmPerson), it returns a List of their names
// we have to calculate a mean rating for the film based on the ratings provided as a Set in our entity.
