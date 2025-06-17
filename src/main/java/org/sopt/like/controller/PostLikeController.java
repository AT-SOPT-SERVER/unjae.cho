package org.sopt.like.controller;

import org.sopt.global.dto.ApiResponse;
import org.sopt.like.service.PostLikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class PostLikeController {
    private final PostLikeService postLikeService;

    public PostLikeController(PostLikeService postLikeService) {
        this.postLikeService = postLikeService;
    }

    @PostMapping("posts/{post-id}/likes")
    public ResponseEntity<?> addLike(
            @RequestHeader Long userId,
            @PathVariable(name = "post-id") final Long postId
    ){
        postLikeService.likePost(userId, postId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponse.create());
    }
}
