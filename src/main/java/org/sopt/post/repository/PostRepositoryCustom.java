package org.sopt.post.repository;

import org.sopt.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {
    Page<Post> searchPosts(String title, String author, Pageable pageable);
}
