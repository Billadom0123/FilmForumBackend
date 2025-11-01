package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.Variety.VarietyPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VarietyRepository extends JpaRepository<VarietyPO, Long> {

    @Query(value = "select distinct v from VarietyPO v " +
            "left join com.example.web.filmforum.Model.Variety.VarietyGuest vg on vg.variety = v " +
            "left join com.example.web.filmforum.Model.Actor.Actor a on a = vg.actor " +
            "where (:keyword is null or v.title like concat('%',:keyword,'%') or v.originalTitle like concat('%',:keyword,'%')) " +
            "and (:tag is null or :tag member of v.tags) " +
            "and (:year is null or v.year = :year) " +
            "and (:actor is null or a.name like concat('%',:actor,'%') or (v.host is not null and v.host.name like concat('%',:actor,'%'))) " +
            "and (:award is null or exists (select ar.id from com.example.web.filmforum.Model.Award.AwardRecordPO ar join ar.award aw where ar.targetId = v.id and aw.targetType = 'VARIETY' and aw.name like concat('%',:award,'%'))) " +
            "and (:minRating is null or (select coalesce(avg(r2.score),0) from com.example.web.filmforum.Model.Ratings.VarietyRating r2 where r2.variety = v) >= :minRating)"
    )
    Page<VarietyPO> queryVarieties(@Param("keyword") String keyword,
                                   @Param("tag") String tag,
                                   @Param("year") Integer year,
                                   @Param("actor") String actor,
                                   @Param("award") String award,
                                   @Param("minRating") Double minRating,
                                   Pageable pageable);

    @Query("select coalesce(avg(r.score),0) from com.example.web.filmforum.Model.Ratings.VarietyRating r where r.variety.id = :varietyId")
    Double getAvgScore(@Param("varietyId") Long varietyId);
}

