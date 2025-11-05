package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.Award.AwardPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AwardRepository extends JpaRepository<AwardPO, Long> {

    @Query("""
            select a from AwardPO a
            where (
                ((:name is null or trim(:name) = '') and (:organization is null or trim(:organization) = '') and (:description is null or trim(:description) = ''))
                or ( (:name is not null and not (trim(:name) = '')) and a.name like concat('%', :name, '%') )
                or ( (:organization is not null and not (trim(:organization) = '')) and a.organization like concat('%', :organization, '%') )
                or ( (:description is not null and not (trim(:description) = '')) and a.description like concat('%', :description, '%') )
            )
            and ((:targetType is null or trim(:targetType) = '') or a.targetType = :targetType)
            """)
    Page<AwardPO> queryAwards(@Param("name") String name,
                              @Param("organization") String organization,
                              @Param("description") String description,
                              @Param("targetType") String targetType,
                              Pageable pageable);

    @Query("""
            select a from AwardPO a
            where (
                ((:name is null or trim(:name) = '') and (:organization is null or trim(:organization) = '') and (:description is null or trim(:description) = ''))
                or ( (:name is not null and not (trim(:name) = '')) and a.name like concat('%', :name, '%') )
                or ( (:organization is not null and not (trim(:organization) = '')) and a.organization like concat('%', :organization, '%') )
                or ( (:description is not null and not (trim(:description) = '')) and a.description like concat('%', :description, '%') )
            )
            """)
    Page<AwardPO> queryAwards(@Param("name") String name,
                              @Param("organization") String organization,
                              @Param("description") String description,
                              Pageable pageable);

    Optional<AwardPO> findByName(String name);
}
