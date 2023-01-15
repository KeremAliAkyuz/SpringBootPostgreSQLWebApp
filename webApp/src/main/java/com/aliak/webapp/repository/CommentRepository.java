package com.aliak.webapp.repository;

import com.aliak.webapp.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findAllByPostId(Long postId);

    List<Comment> findAllByPostIdAndUserId(Long postId, Long userId);//JPA Repository olduğu için implement etmeye gerek yok

    List<Comment> findAllByUserId(Long userId);
}
