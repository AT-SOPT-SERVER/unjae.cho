package org.sopt.comment.service;

import org.sopt.comment.dto.CommentRequestDto;
import org.sopt.comment.dto.CommentResponseDto;
import org.sopt.comment.domain.Comment;
import org.sopt.comment.repository.CommentRepository;
import org.sopt.comment.utils.CommentValidator;
import org.sopt.global.exception.custom.*;
import org.sopt.post.domain.Post;
import org.sopt.post.repository.PostRepository;
import org.sopt.user.domain.User;
import org.sopt.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentService(
            CommentRepository commentRepository,
            UserRepository userRepository,
            PostRepository postRepository
    ) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public CommentResponseDto createComment(
            Long userId,
            Long postId,
            CommentRequestDto commentRequest
    ) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        CommentValidator.validateComment(commentRequest.content());
        Comment comment = new Comment(commentRequest.content(), user, post);

        return new CommentResponseDto(commentRepository.save(comment));
    }

    @Transactional
    public CommentResponseDto updateComment(
            Long userId,
            Long commentId,
            CommentRequestDto commentRequest
    ){
        CommentValidator.validateComment(commentRequest.content());

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        if (comment.getAuthor().equals(user)) {
            comment.setContent(commentRequest.content());
            return new CommentResponseDto(commentRepository.save(comment));
        }
        else {
            throw new UnauthorizedException();
        }
    }

    @Transactional
    public void deleteComment(
            Long userId,
            Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        if (comment.getAuthor().equals(user)) {
            commentRepository.delete(comment);
        }
        else {
            throw new UnauthorizedException();
        }
    }
}
