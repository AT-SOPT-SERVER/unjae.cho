package org.sopt.post.repository;

import org.sopt.post.domain.Post;
import org.sopt.post.dto.PostResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<PostResponseDto> findByTitleContaining(String title);
}