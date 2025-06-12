package org.sopt.comment.controller;

import org.sopt.comment.dto.CommentRequestDto;
import org.sopt.comment.dto.CommentResponseDto;
import org.sopt.comment.service.CommentService;
import org.sopt.global.dto.ApiResponse;
import org.sopt.post.dto.PostRequestDto;
import org.sopt.post.dto.PostResponseDto;
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
            @RequestBody final CommentRequestDto commentRequestDto
    ){
        CommentResponseDto comment = commentService.createComment(userId, postId, commentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.create(comment));
    }
}
