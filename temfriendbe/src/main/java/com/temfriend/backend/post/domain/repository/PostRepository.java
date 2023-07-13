package com.temfriend.backend.post.domain.repository;

import com.temfriend.backend.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long> {
}