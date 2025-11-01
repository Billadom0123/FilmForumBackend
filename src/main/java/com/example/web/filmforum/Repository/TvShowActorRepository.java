package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.TvShow.TvShowActor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TvShowActorRepository extends JpaRepository<TvShowActor, Long> {
    List<TvShowActor> findByTvShow_Id(Long tvShowId);
    List<TvShowActor> findByActor_Id(Long actorId);
}

