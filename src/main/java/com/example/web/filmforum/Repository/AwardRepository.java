package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.Award.AwardPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AwardRepository extends JpaRepository<AwardPO, Long> {

    @Query("""
            select a from AwardPO a
            where (upper(a.name) like concat('%', upper(?1), '%')
            or upper(a.organization) like concat('%', upper(?2), '%')
            or upper(a.description) like concat('%', upper(?3), '%'))
            and upper(a.targetType) = upper(?4)
            """)
    Page<AwardPO> queryAwards(String name, String organization, String description, String targetType, Pageable pageable);

    @Query("""
            select a from AwardPO a
            where upper(a.name) like concat('%', upper(?1), '%')
            or upper(a.organization) like concat('%', upper(?2), '%')
            or upper(a.description) like concat('%', upper(?3), '%')
            """)
    Page<AwardPO> queryAwards(String name, String organization, String description, Pageable pageable);

}
