package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.Post.PostPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostPO, Long> {

    @Query("select p from PostPO p " +
            "where (:category is null or p.category = :category) " +
            "and (:keyword is null or " +
            "(p.title like concat('%', :keyword, '%') " +
            "or p.content like concat('%', :keyword, '%'))) " +
            "order by p.createTime desc")
    Page<PostPO> search(@Param("category") String category,
                        @Param("keyword") String keyword,
                        Pageable pageable);
}
