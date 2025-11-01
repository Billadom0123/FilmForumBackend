package com.example.web.filmforum.Model.TvShow;

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
@Table(name = "tv_show_likes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "tv_show_id"})
})
public class TvShowLikePO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserPO user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tv_show_id", nullable = false)
    private TvShowPO tvShow;

    private LocalDateTime createTime = LocalDateTime.now();
}

