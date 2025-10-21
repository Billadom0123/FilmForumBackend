package com.example.web.filmforum.Model.Ratings;

import com.example.web.filmforum.Model.Film.FilmPO;
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
public class FilmRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score;

    private String userId;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private FilmPO film;
}
