package org.sopt.like.controller;

import org.sopt.auth.annotation.UserId;
import org.sopt.global.dto.ApiResponse;
import org.sopt.like.service.CommentLikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/posts/{post-id}/comments")
public class CommentLikeController {
    private final CommentLikeService commentLikeService;

    public CommentLikeController(CommentLikeService commentLikeService) {
        this.commentLikeService = commentLikeService;
    }

    @PostMapping("/{comment-id}/likes")
    public ResponseEntity<?> addLike(
            @UserId Long userId,
            @PathVariable(name = "comment-id") final Long commentId
    ){
        commentLikeService.likeComment(userId, commentId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponse.create());
    }
}
