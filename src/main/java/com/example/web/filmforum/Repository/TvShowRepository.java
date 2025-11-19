package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.TvShow.TvShowPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TvShowRepository extends JpaRepository<TvShowPO, Long> {

    @Query(value = "select distinct t from TvShowPO t " +
            "left join TvShowActor ta on ta.tvShow = t " +
            "left join Actor a on a = ta.actor " +
            "where ((:keyword is null or trim(:keyword) = '') or t.title like concat('%',:keyword,'%') or t.originalTitle like concat('%',:keyword,'%')) " +
            "and ((:tag is null or trim(:tag) = '') or :tag member of t.tags) " +
            "and (:year is null or t.year = :year) " +
            "and ((:actor is null or trim(:actor) = '') or a.name like concat('%',:actor,'%')) " +
            "and ((:award is null or trim(:award) = '') or exists (select ar.id from AwardRecordPO ar join ar.award aw where ar.targetId = t.id and aw.targetType = 'TVSHOW' and aw.name like concat('%',:award,'%'))) " +
            "and (:minRating is null or (select coalesce(rs.ratingAvg,0) from RatingStatPO rs where rs.targetType = 'TVSHOW' and rs.targetId = t.id) >= :minRating)"
    )
    Page<TvShowPO> queryShows(@Param("keyword") String keyword,
                              @Param("tag") String tag,
                              @Param("year") Integer year,
                              @Param("actor") String actor,
                              @Param("award") String award,
                              @Param("minRating") Double minRating,
                              Pageable pageable);

    @Query("select coalesce((select rs.ratingAvg from RatingStatPO rs where rs.targetType = 'TVSHOW' and rs.targetId = :showId), 0)")
    Double getAvgScore(@Param("showId") Long showId);

    @Modifying
    @Query("update TvShowPO t set t.director = null where t.director.id = :actorId")
    int clearDirectorReferences(@Param("actorId") Long actorId);
}
