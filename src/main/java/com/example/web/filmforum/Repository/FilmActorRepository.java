package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.Film.FilmActor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmActorRepository extends JpaRepository<FilmActor, Long> {
    List<FilmActor> findByFilm_Id(Long filmId);
    List<FilmActor> findByActor_Id(Long actorId);

    @Modifying
    @Query("delete from FilmActor fa where fa.actor.id = :actorId")
    int deleteByActorId(@Param("actorId") Long actorId);
}
