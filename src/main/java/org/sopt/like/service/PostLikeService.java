package org.sopt.like.service;

import org.sopt.global.exception.custom.DuplicatedLikeException;
import org.sopt.global.exception.custom.LikeNullException;
import org.sopt.like.repository.PostLikeRepository;
import org.sopt.like.domain.PostLike;
import org.sopt.post.domain.Post;
import org.sopt.post.repository.PostRepository;
import org.sopt.user.domain.User;
import org.sopt.global.exception.custom.PostNotFoundException;
import org.sopt.global.exception.custom.UserNotFoundException;
import org.sopt.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
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

    @Transactional
    public void removeLike(Long userId, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow((PostNotFoundException::new));

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        if (postLikeRepository.existsByPostAndUser(post, user)) {
            PostLike postLikes = postLikeRepository.findByPostAndUser(post, user);
            postLikeRepository.delete(postLikes);
        } else {
            throw new LikeNullException();
        }
    }
}

