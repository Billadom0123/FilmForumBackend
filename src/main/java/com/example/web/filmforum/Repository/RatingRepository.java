package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.Common.RatingPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<RatingPO, Long> {
    boolean existsByUser_IdAndTargetTypeAndTargetId(Long userId, String targetType, Long targetId);
    RatingPO findByUser_IdAndTargetTypeAndTargetId(Long userId, String targetType, Long targetId);

    long countByTargetTypeAndTargetId(String targetType, Long targetId);

    @Query("select coalesce(avg(r.score),0) from com.example.web.filmforum.Model.Common.RatingPO r where r.targetType = :t and r.targetId = :id")
    Double avgByTarget(@Param("t") String targetType, @Param("id") Long targetId);
}
