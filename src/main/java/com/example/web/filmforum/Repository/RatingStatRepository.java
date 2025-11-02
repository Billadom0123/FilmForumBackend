package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.Common.RatingStatPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingStatRepository extends JpaRepository<RatingStatPO, Long> {
    RatingStatPO findByTargetTypeAndTargetId(String targetType, Long targetId);
    List<RatingStatPO> findByTargetTypeAndTargetIdIn(String targetType, List<Long> targetIds);
}
