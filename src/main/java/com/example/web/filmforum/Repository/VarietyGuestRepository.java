package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.Variety.VarietyGuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VarietyGuestRepository extends JpaRepository<VarietyGuest, Long> {
    List<VarietyGuest> findByVariety_Id(Long varietyId);
    List<VarietyGuest> findByActor_Id(Long actorId);

    @Modifying
    @Query("delete from VarietyGuest vg where vg.actor.id = :actorId")
    int deleteByActorId(@Param("actorId") Long actorId);
}
