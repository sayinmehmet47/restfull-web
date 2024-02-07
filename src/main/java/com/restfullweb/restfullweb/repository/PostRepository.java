package com.restfullweb.restfullweb.repository;


import com.restfullweb.restfullweb.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUserId(int userId);
}
