package com.example.web.filmforum.Model.Award;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "award_record")
public class AwardRecordPO {
    private Long id;

    @ManyToOne
    @JoinColumn(name = "award_id")
    private AwardPO award;

    private int year;

    private Long targetId;

    private String status; // e.g., "nominated", "awarded"

    private String note;

}
