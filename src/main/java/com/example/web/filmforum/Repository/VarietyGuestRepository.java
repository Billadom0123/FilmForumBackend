package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.Variety.VarietyGuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VarietyGuestRepository extends JpaRepository<VarietyGuest, Long> {
    List<VarietyGuest> findByVariety_Id(Long varietyId);
}

