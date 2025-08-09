package com.example.demo.entity;


import java.time.LocalDate;
import java.util.*;
import org.springframework.format.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "film")
public class Film {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @Column(name = "name",nullable = true)
   private String name;

   @Column(name = "description", columnDefinition = "TEXT", length = 200, nullable = true)
   private String description;

   @Column(name = "premiere_date", columnDefinition = "DATE")
   @DateTimeFormat(pattern = "yyyy-MM-dd")
   @NotNull
   private LocalDate premiereDate;

   @ManyToMany(fetch = FetchType.LAZY, cascade =
           {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
   @JoinTable(name = "film_genre",
           joinColumns = @JoinColumn(name = "film_id"),
           inverseJoinColumns = @JoinColumn(name = "genre_id"))
   private Set<Genre> genres = new HashSet<>();

   @Column(name = "duration")
   @NotNull
   @Positive
   private int duration;

   @NotNull
   @Column(nullable = true, length = 255)

   @ManyToOne(cascade =
           {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
   @JoinColumn(name = "mpaa_id")
   private Mpaa mpaa;

   @ManyToMany(fetch = FetchType.LAZY, cascade =
           {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
   @JoinTable(name = "film_actor",      
           joinColumns = @JoinColumn(name = "film_id"),
           inverseJoinColumns = @JoinColumn(name = "star_id"))
   private Set<FilmPerson> actors = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id")
    private Set<Rating> ratings = new HashSet<>();


@Override
public boolean equals(Object o) {
   if (this == o) return true;
   if (o == null || getClass() != o.getClass()) return false;
   Film film = (Film) o;
   return Objects.equals(id, film.id);
}

@Override
public int hashCode() {
   return Objects.hash(id);
}
}