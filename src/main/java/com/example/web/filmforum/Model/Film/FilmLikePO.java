package com.example.web.filmforum.Model.Film;

import com.example.web.filmforum.Model.User.UserPO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "film_likes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "film_id"})
})
public class FilmLikePO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserPO user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "film_id", nullable = false)
    private FilmPO film;

    private LocalDateTime createTime = LocalDateTime.now();
}

