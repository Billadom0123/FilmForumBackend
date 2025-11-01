package com.example.web.filmforum.Model.Variety;

import com.example.web.filmforum.Model.Actor.Actor;
import com.example.web.filmforum.Model.Ratings.VarietyRating;
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
@Table(name = "variety")
public class VarietyPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String originalTitle;

    private int year;

    private int episodes;

    @OneToMany(mappedBy = "variety", cascade = CascadeType.ALL)
    private List<VarietyRating> ratings;

    private String poster;

    @Size(max = 1000)
    private String summary;

    @ManyToOne
    @JoinColumn(name = "host_id")
    private Actor host;

    private String country;

    private String language;

    private String trailer;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = String.class)
    @CollectionTable(name = "variety_photos", joinColumns = @JoinColumn(name = "variety_id"))
    @Column(name = "photo")
    private List<String> photos;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = String.class)
    @CollectionTable(name = "variety_tags", joinColumns = @JoinColumn(name = "variety_id"))
    @Column(name = "tag")
    private List<String> tags;

    private int views;
}

