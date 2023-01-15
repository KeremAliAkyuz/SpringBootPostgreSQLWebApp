package com.aliak.webapp.repository;

import com.aliak.webapp.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {

    List<Like> findAllByUserId(Long userId);

    List<Like> findAllByUserIdAndPostId(Long userId, Long postId );

    List<Like> findAllByPostId(Long postId);
}
