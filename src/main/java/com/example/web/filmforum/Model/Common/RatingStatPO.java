package com.example.web.filmforum.Model.Common;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rating_stats", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"target_type", "target_id"})
})
public class RatingStatPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "target_type", nullable = false)
    private String targetType;

    @Column(name = "target_id", nullable = false)
    private Long targetId;

    @Column(nullable = false)
    private long ratingCount = 0L;

    @Column(nullable = false)
    private long ratingSum = 0L;

    @Column(nullable = false)
    private double ratingAvg = 0.0;
}

