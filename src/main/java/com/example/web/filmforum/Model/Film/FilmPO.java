package com.example.web.filmforum.Model.Film;

import com.example.web.filmforum.Model.Actor.Actor;
import com.example.web.filmforum.Model.Ratings.FilmRating;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "film")
public class FilmPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String originalTitle;

    private int year;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    private List<FilmRating> ratings;

    private String poster;

    @Size(max = 1000)
    private String summary;

    private int duration; // minutes

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Actor director;

    private String country;

    private String language;

    private String trailer;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = String.class)
    @CollectionTable(name = "film_photos", joinColumns = @JoinColumn(name = "film_id"))
    @Column(name = "photo")
    private List<String> photos;

    private int views;




}
