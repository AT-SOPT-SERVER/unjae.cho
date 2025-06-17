package org.sopt.like.service;

import org.sopt.global.exception.custom.DuplicatedLikeException;
import org.sopt.like.repository.PostLikeRepository;
import org.sopt.like.domain.PostLike;
import org.sopt.post.domain.Post;
import org.sopt.post.repository.PostRepository;
import org.sopt.user.domain.User;
import org.sopt.global.exception.custom.PostNotFoundException;
import org.sopt.global.exception.custom.UserNotFoundException;
import org.sopt.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class PostLikeService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostLikeRepository postLikeRepository;

    public PostLikeService(PostRepository postRepository,
                           UserRepository userRepository,
                           PostLikeRepository postLikeRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postLikeRepository = postLikeRepository;
    }

    public void likePost(Long userId, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow((PostNotFoundException::new));

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        if(!postLikeRepository.existsByPostAndUser(post, user)) {
            PostLike postLike = new PostLike(post, user);
            postLikeRepository.save(postLike);
        }
        else{
            throw new DuplicatedLikeException();
        }
    }
}

