package org.sopt.like.repository;

import org.sopt.like.domain.PostLike;
import org.sopt.post.domain.Post;
import org.sopt.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    boolean existsByPostAndUser(Post post, User user);
    PostLike findByPostAndUser(Post post, User user);
}
