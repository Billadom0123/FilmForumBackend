package com.example.web.filmforum.Model.TvShow;

import com.example.web.filmforum.Model.Actor.Actor;
import com.example.web.filmforum.Model.Ratings.TvShowRating;
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
@Table(name = "tv_show")
public class TvShowPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String originalTitle;

    private int year;

    private int episodes;

    @OneToMany(mappedBy = "tvShow", cascade = CascadeType.ALL)
    private List<TvShowRating> ratings;

    private String poster;

    @Size(max = 1000)
    private String summary;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Actor director;

    private String country;

    private String language;

    private String trailer;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = String.class)
    @CollectionTable(name = "tv_show_photos", joinColumns = @JoinColumn(name = "tv_show_id"))
    @Column(name = "photo")
    private List<String> photos;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = String.class)
    @CollectionTable(name = "tv_show_tags", joinColumns = @JoinColumn(name = "tv_show_id"))
    @Column(name = "tag")
    private List<String> tags;

    private int views;
}