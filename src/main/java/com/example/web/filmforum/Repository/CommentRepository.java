package com.example.web.filmforum.Repository;

import com.example.web.filmforum.Model.Post.CommentPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentPO, Long> {
    Page<CommentPO> findByPost_IdAndParentIsNullOrderByCreateTimeDesc(Long postId, Pageable pageable);
    List<CommentPO> findByParent_IdOrderByCreateTimeAsc(Long parentId);
    long countByPost_Id(Long postId);
}

