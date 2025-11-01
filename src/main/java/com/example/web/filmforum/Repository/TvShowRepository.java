package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.TvShow.TvShowPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TvShowRepository extends JpaRepository<TvShowPO, Long> {

    @Query(value = "select distinct t from TvShowPO t " +
            "left join com.example.web.filmforum.Model.TvShow.TvShowActor ta on ta.tvShow = t " +
            "left join com.example.web.filmforum.Model.Actor.Actor a on a = ta.actor " +
            "where (:keyword is null or t.title like concat('%',:keyword,'%') or t.originalTitle like concat('%',:keyword,'%')) " +
            "and (:tag is null or :tag member of t.tags) " +
            "and (:year is null or t.year = :year) " +
            "and (:actor is null or a.name like concat('%',:actor,'%')) " +
            "and (:award is null or exists (select ar.id from com.example.web.filmforum.Model.Award.AwardRecordPO ar join ar.award aw where ar.targetId = t.id and aw.targetType = 'TVSHOW' and aw.name like concat('%',:award,'%'))) " +
            "and (:minRating is null or (select coalesce(avg(r2.score),0) from com.example.web.filmforum.Model.Ratings.TvShowRating r2 where r2.tvShow = t) >= :minRating)"
    )
    Page<TvShowPO> queryShows(@Param("keyword") String keyword,
                              @Param("tag") String tag,
                              @Param("year") Integer year,
                              @Param("actor") String actor,
                              @Param("award") String award,
                              @Param("minRating") Double minRating,
                              Pageable pageable);

    @Query("select coalesce(avg(r.score),0) from com.example.web.filmforum.Model.Ratings.TvShowRating r where r.tvShow.id = :showId")
    Double getAvgScore(@Param("showId") Long showId);
}

