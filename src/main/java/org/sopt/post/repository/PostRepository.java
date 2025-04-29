package org.sopt.post.repository;

import org.sopt.post.domain.Post;
import org.sopt.post.dto.PostResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<PostResponse> findByTitleContaining(@RequestParam String title);
}