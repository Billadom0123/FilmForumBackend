package com.example.web.filmforum.Model.Award;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "award")
public class AwardPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                // 奖项ID

    private String name;            // 奖项名称

    private String organization;    // 颁奖机构

    private String targetType;      // 获奖对象类型（ACTOR、FILM、TVSHOW、VARIETY）

    private String description;     // 备注
}
