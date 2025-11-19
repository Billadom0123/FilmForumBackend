package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.Variety.VarietyPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VarietyRepository extends JpaRepository<VarietyPO, Long> {

    @Query(value = "select distinct v from VarietyPO v " +
            "left join VarietyGuest vg on vg.variety = v " +
            "left join Actor a on a = vg.actor " +
            "where ((:keyword is null or trim(:keyword) = '') or v.title like concat('%',:keyword,'%') " +
            "or v.originalTitle like concat('%',:keyword,'%')) " +
            "and ((:tag is null or trim(:tag) = '') or :tag member of v.tags) " +
            "and (:year is null or v.year = :year) " +
            "and ((:actor is null or trim(:actor) = '') or (a.name like concat('%',:actor,'%'))) " +
            "and ((:award is null or trim(:award) = '') or exists (select ar.id from AwardRecordPO ar join ar.award aw where ar.targetId = v.id and aw.targetType = 'VARIETY' and aw.name like concat('%',:award,'%'))) " +
            "and (:minRating is null or (select coalesce(rs.ratingAvg,0) from RatingStatPO rs where rs.targetType = 'VARIETY' and rs.targetId = v.id) >= :minRating)"
    )
    Page<VarietyPO> queryVarieties(@Param("keyword") String keyword,
                                   @Param("tag") String tag,
                                   @Param("year") Integer year,
                                   @Param("actor") String actor,
                                   @Param("award") String award,
                                   @Param("minRating") Double minRating,
                                   Pageable pageable);

    @Query("select coalesce(avg(r.score),0) from RatingPO r where r.targetType = 'VARIETY' and r.targetId = :varietyId")
    Double getAvgScore(@Param("varietyId") Long varietyId);

    @Modifying
    @Query("update VarietyPO v set v.host = null where v.host.id = :actorId")
    int clearHostReferences(@Param("actorId") Long actorId);
}
