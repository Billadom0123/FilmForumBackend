package com.example.web.filmforum.Model.Common;

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
@Table(name = "favorites", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "target_type", "target_id"})
})
public class FavoritePO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserPO user;

    @Column(name = "target_type", nullable = false)
    private String targetType; // FILM / TVSHOW / VARIETY

    @Column(name = "target_id", nullable = false)
    private Long targetId;

    private LocalDateTime createTime = LocalDateTime.now();
}

