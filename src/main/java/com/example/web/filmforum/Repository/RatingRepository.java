package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.Common.RatingPO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface RatingRepository extends JpaRepository<RatingPO, Long> {
    boolean existsByUser_IdAndTargetTypeAndTargetId(Long userId, String targetType, Long targetId);
    RatingPO findByUser_IdAndTargetTypeAndTargetId(Long userId, String targetType, Long targetId);

    long countByTargetTypeAndTargetId(String targetType, Long targetId);

    @Query("select coalesce(avg(r.score),0) from com.example.web.filmforum.Model.Common.RatingPO r where r.targetType = :t and r.targetId = :id")
    Double avgByTarget(@Param("t") String targetType, @Param("id") Long targetId);

    // 新增：按目标删除全部评分
    @Modifying
    @Transactional
    void deleteByTargetTypeAndTargetId(String targetType, Long targetId);

    // 新增：分页获取有短评的评分记录
    Page<RatingPO> findByTargetTypeAndTargetIdAndCommentIsNotNull(String targetType, Long targetId, Pageable pageable);
}
