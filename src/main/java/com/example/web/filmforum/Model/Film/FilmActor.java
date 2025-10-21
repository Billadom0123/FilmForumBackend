package com.example.web.filmforum.Model.Film;

import com.example.web.filmforum.Model.Actor.Actor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FilmActor {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private FilmPO film;

    @ManyToOne
    @JoinColumn(name = "actor_id")
    private Actor actor;

    private String role; // e.g., "Lead", "Supporting", etc.
    private String description;
}
