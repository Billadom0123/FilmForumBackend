package com.example.web.filmforum.Model.Award;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "award_record")
public class AwardRecordPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "award_id")
    private AwardPO award;

    private int year;

    private Long targetId;

    private String status; // e.g., "nominated", "awarded"

    private String note;

}
