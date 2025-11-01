package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.TvShow.TvShowSeason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TvShowSeasonRepository extends JpaRepository<TvShowSeason, Long> {
    List<TvShowSeason> findByTvShow_IdOrderByNumberAsc(Long tvShowId);
}

