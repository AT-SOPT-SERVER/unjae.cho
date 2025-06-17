package org.sopt.comment.controller;

import jakarta.validation.Valid;
import org.sopt.comment.dto.CommentRequestDto;
import org.sopt.comment.dto.CommentResponseDto;
import org.sopt.comment.service.CommentService;
import org.sopt.global.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("posts/{post-id}/comments")
    public ResponseEntity<ApiResponse<CommentResponseDto>> createComment(
            @RequestHeader Long userId,
            @PathVariable(name = "post-id") final Long postId,
            @RequestBody final @Valid CommentRequestDto commentRequestDto
    ){
        CommentResponseDto comment = commentService.createComment(userId, postId, commentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.create(comment));
    }

    @PatchMapping("posts/{post-id}/comments/{comment-id}")
    public ResponseEntity<ApiResponse<CommentResponseDto>> updateComment(
            @RequestHeader Long userId,
            @PathVariable(name = "comment-id") final Long commentId,
            @RequestBody final @Valid CommentRequestDto commentRequestDto
    ){
        CommentResponseDto comment = commentService.updateComment(userId, commentId, commentRequestDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponse.update(comment));
    }

    @DeleteMapping("posts/{post-id}/comments/{comment-id}")
    public ResponseEntity<ApiResponse<Void>> deleteComment(
            @RequestHeader Long userId,
            @PathVariable(name = "comment-id") final Long commentId
    ){
        commentService.deleteComment(userId, commentId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponse.delete());
    }

}
