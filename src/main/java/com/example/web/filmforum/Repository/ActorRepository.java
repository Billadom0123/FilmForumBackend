package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.Actor.Actor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActorRepository extends JpaRepository<Actor, Long> {

    Page<Actor> findByName(String name, Pageable pageable);

    @Query("""
            select a from Actor a
            where (((:name is null or trim(:name) = '') or (a.name like concat('%', :name, '%')))
            or ((:biography is null or trim(:biography) = '') or (a.biography like concat('%', :biography, '%'))))
            and ((:nationality is null or trim(:nationality) = '') or (a.nationality = :nationality))
            and ((:gender is null or trim(:gender) = '') or (a.gender = :gender))
            """)
    Page<Actor> queryActors(@Param("name") String name,
                            @Param("biography") String biography,
                            @Param("nationality") String nationality,
                            @Param("gender") String gender,
                            Pageable pageable);
}
