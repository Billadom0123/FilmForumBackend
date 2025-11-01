package com.example.web.filmforum.Model.TvShow;

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
@Table(name = "tv_show_season")
public class TvShowSeason {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tv_show_id")
    private TvShowPO tvShow;

    private int number;

    private String title;

    private int episodes;

    private int year;
}

