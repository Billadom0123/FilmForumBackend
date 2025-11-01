package com.example.web.filmforum.Model.Variety;

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
public class VarietyGuest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "variety_id")
    private VarietyPO variety;

    @ManyToOne
    @JoinColumn(name = "actor_id")
    private Actor actor;

    private String role;
    private String description;
}

