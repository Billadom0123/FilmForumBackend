package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.Award.AwardRecordPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AwardRecordRepository extends JpaRepository<AwardRecordPO, Long> {
}
