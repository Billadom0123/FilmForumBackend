package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.Actor.Actor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActorRepository extends JpaRepository<Actor, Long> {

    Page<Actor> findByName(String name, Pageable pageable);

    @Query("""
            select a from Actor a
            where (upper(a.name) like concat('%', upper(?1), '%')
            or upper(a.biography) like concat('%', upper(?2), '%'))
            and upper(a.nationality) = upper(?3)
            and upper(a.gender) = upper(?4)""")
    Page<Actor> queryActors(String name, String biography, String nationality, String gender, Pageable pageable);
}
