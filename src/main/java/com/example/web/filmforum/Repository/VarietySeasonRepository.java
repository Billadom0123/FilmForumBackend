package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.Variety.VarietySeason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VarietySeasonRepository extends JpaRepository<VarietySeason, Long> {
    List<VarietySeason> findByVariety_IdOrderByNumberAsc(Long varietyId);
}

