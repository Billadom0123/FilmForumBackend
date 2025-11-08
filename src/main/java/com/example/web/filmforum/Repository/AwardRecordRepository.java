package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.Award.AwardRecordPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AwardRecordRepository extends JpaRepository<AwardRecordPO, Long> {
    List<AwardRecordPO> findByTargetIdAndAward_TargetType(Long targetId, String targetType);
    List<AwardRecordPO> findByAward_Id(Long awardId);
}
