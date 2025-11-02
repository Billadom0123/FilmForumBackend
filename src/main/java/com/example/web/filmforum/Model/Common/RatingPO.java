package com.example.web.filmforum.Model.Common;

import com.example.web.filmforum.Model.User.UserPO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ratings", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "target_type", "target_id"})
})
public class RatingPO {
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

    @Column(nullable = false)
    private int score; // 1-10

    @Column(length = 1000)
    private String comment;

    private LocalDateTime createTime = LocalDateTime.now();

    private LocalDateTime updateTime = LocalDateTime.now();

    @PreUpdate
    public void onUpdate() {
        this.updateTime = LocalDateTime.now();
    }
}

