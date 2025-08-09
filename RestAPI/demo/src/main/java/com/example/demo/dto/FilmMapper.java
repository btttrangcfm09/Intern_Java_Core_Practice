package com.example.demo.dto;
import com.example.demo.entity.*;
import java.util.stream.Collectors.*;
import lombok.*;
import com.example.demo.repository.*;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
@Getter
@Setter
@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
       componentModel = "spring") 
// componentModel = "spring": turn Mapper into Spring Bean and be able to inject it via @Autowired
public abstract class FilmMapper {
    // abstract class enable us to customize the mapping methods
    // Remove the INSTANCE field because the Mapper is a normal Spring bean now 
    // and should be directly added to classes using it;
   protected ReferenceFinderRepository repository;
   @Autowired
   protected void setReferenceFinderRepository(ReferenceFinderRepository repository) {
       this.repository = repository;
   }

   public FilmDto mapToFilmResponse(Film film) {
       FilmDto.FilmDtoBuilder filmDto = FilmDto.builder()
               .name(film.getName())
               .description(film.getDescription())
               .duration(film.getDuration())
               .premiereDate(film.getPremiereDate())
               .mpaa(film.getMpaa().getName())
               .genres(film.getGenres().stream().map(Genre::getName).collect(Collectors.toList()))
               .actors(film.getActors().stream().map(FilmPerson::getName).collect(Collectors.toList()));
            double meanRating = 0.0;
            if (!film.getRatings().isEmpty()) {
                meanRating = film.getRatings()
                    .stream()
                    .map(Rating::getPoints)
                    .mapToInt(Integer::intValue)
                    .summaryStatistics()
                    .getAverage();
            }            
            filmDto.rating(Math.round(meanRating*100.0)/100.0);
            return filmDto.build();
   }

   public Film mapToFilm(NewFilmRequest newFilmRequest) {
       Film.FilmBuilder film = Film.builder()
               .name(newFilmRequest.name())
               .description(newFilmRequest.description())
               .premiereDate(newFilmRequest.premiereDate())
               .duration(newFilmRequest.duration())
               .mpaa(repository.getMpaaReference(newFilmRequest.mpaa()));

       film.genres(newFilmRequest.genreIds()
               .stream()
               .map(repository::getGenreReference)
               .collect(Collectors.toSet()));
               
       film.actors(newFilmRequest.actorIds()
               .stream()
               .map(repository::getFilmPersonReference)
               .collect(Collectors.toSet()));



        

       return film.build();
   }
}