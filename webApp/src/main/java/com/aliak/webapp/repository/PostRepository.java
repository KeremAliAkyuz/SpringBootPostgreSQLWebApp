package com.aliak.webapp.repository;

import com.aliak.webapp.entities.Post;
import com.aliak.webapp.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByUserId(Long userId);//findByUserId yaz gerisini JPA halleder
    //bu userId'ye sahip tüm postları döner.
}
