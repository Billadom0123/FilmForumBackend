package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.Film.FilmPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<FilmPO, Long> {


    @Query(value = "select distinct f from FilmPO f " +
            "left join com.example.web.filmforum.Model.Film.FilmActor fa on fa.film = f " +
            "left join com.example.web.filmforum.Model.Actor.Actor a on a = fa.actor " +
            "where (:keyword is null or f.title like concat('%',:keyword,'%') " +
            "or f.originalTitle like concat('%',:keyword,'%')) " +
            "and (:tag is null or :tag member of f.tags) " +
            "and (:year is null or f.year = :year) " +
            "and (:actor is null or a.name like concat('%',:actor,'%')) " +
            "and (:award is null or exists (select ar.id from com.example.web.filmforum.Model.Award.AwardRecordPO ar join ar.award aw where ar.targetId = f.id and aw.targetType = 'FILM' and aw.name like concat('%',:award,'%'))) " +
            "and (:minRating is null or (select coalesce(avg(r2.score),0) from com.example.web.filmforum.Model.Ratings.FilmRating r2 where r2.film = f) >= :minRating)"
    )
    Page<FilmPO> queryMovies(@Param("keyword") String keyword,
                             @Param("tag") String tag,
                             @Param("year") Integer year,
                             @Param("actor") String actor,
                             @Param("award") String award,
                             @Param("minRating") Double minRating,
                             Pageable pageable);

    @Query("select coalesce(avg(r.score),0) from com.example.web.filmforum.Model.Ratings.FilmRating r where r.film.id = :filmId")
    Double getAvgScore(@Param("filmId") Long filmId);
}
