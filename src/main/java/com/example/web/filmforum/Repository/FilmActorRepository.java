package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.Film.FilmActor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmActorRepository extends JpaRepository<FilmActor, Long> {
    List<FilmActor> findByFilm_Id(Long filmId);
}

