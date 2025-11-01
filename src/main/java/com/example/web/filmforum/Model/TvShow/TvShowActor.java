package com.example.web.filmforum.Model.TvShow;

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
public class TvShowActor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tv_show_id")
    private TvShowPO tvShow;

    @ManyToOne
    @JoinColumn(name = "actor_id")
    private Actor actor;

    private String role;
    private String description;
}

