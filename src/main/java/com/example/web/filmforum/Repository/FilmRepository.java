package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.Film.FilmPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<FilmPO, Long> {
}
