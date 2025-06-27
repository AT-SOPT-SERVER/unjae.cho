package org.sopt.like.service;

import org.sopt.comment.domain.Comment;
import org.sopt.global.exception.custom.*;
import org.sopt.comment.repository.CommentRepository;
import org.sopt.like.domain.CommentLike;
import org.sopt.like.repository.CommentLikeRepository;
import org.sopt.user.domain.User;
import org.sopt.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentLikeService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CommentLikeRepository commentLikeRepository;

    public CommentLikeService(
            CommentRepository commentRepository,
            UserRepository userRepository,
            CommentLikeRepository commentLikeRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.commentLikeRepository = commentLikeRepository;
    }

    @Transactional
    public void likeComment(Long userId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow((CommentNotFoundException::new));

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        if(!commentLikeRepository.existsByCommentAndUser(comment, user)) {
            CommentLike commentLike = new CommentLike(comment, user);
            commentLikeRepository.save(commentLike);
        }
        else{
            throw new DuplicatedLikeException();
        }
    }

    @Transactional
    public void removeLike(Long userId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow((PostNotFoundException::new));

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        if (commentLikeRepository.existsByCommentAndUser(comment, user)) {
            CommentLike commentLike = commentLikeRepository.findByCommentAndUser(comment, user);
            commentLikeRepository.delete(commentLike);
        } else {
            throw new LikeNullException();
        }
    }
}
