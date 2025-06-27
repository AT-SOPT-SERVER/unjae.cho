package org.sopt.comment.controller;

import org.sopt.auth.annotation.UserId;
import org.sopt.comment.dto.CommentRequestDto;
import org.sopt.comment.dto.CommentResponseDto;
import org.sopt.comment.service.CommentService;
import org.sopt.global.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/posts")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{post-id}/comments")
    public ResponseEntity<ApiResponse<CommentResponseDto>> createComment(
            @UserId Long userId,
            @PathVariable(name = "post-id") final Long postId,
            @RequestBody final CommentRequestDto commentRequestDto
    ){
        CommentResponseDto comment = commentService.createComment(userId, postId, commentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.create(comment));
    }

    @PatchMapping("/{post-id}/comments/{comment-id}")
    public ResponseEntity<ApiResponse<CommentResponseDto>> updateComment(
            @UserId Long userId,
            @PathVariable(name = "comment-id") final Long commentId,
            @RequestBody final CommentRequestDto commentRequestDto
    ){
        CommentResponseDto comment = commentService.updateComment(userId, commentId, commentRequestDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponse.update(comment));
    }

    @DeleteMapping("/{post-id}/comments/{comment-id}")
    public ResponseEntity<ApiResponse<Void>> deleteComment(
            @UserId Long userId,
            @PathVariable(name = "comment-id") final Long commentId
    ){
        commentService.deleteComment(userId, commentId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponse.delete());
    }

}
