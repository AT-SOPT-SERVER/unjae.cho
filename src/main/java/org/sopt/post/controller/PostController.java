package org.sopt.post.controller;

import org.sopt.auth.annotation.UserId;
import org.sopt.global.dto.ApiResponse;
import org.sopt.global.dto.PageResponse;
import org.sopt.post.dto.PostDetailResponseDto;
import org.sopt.post.dto.PostRequestDto;
import org.sopt.post.dto.PostResponseDto;
import org.sopt.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/posts")
public class PostController {

    private final PostService postService;

    public PostController(
            PostService postService
    ) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PostResponseDto>> createPost(
            @UserId final Long userId,
            @RequestBody final PostRequestDto postRequestDto
    ) {
        PostResponseDto post = postService.createPost(userId, postRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.create(post));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<PostResponseDto>>> getAllPosts(
            @UserId final Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<PostResponseDto> posts = postService.getAllPosts(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.read(posts));
    }

    @GetMapping("/{post-id}")
    public ResponseEntity<ApiResponse<PostDetailResponseDto>> getPostById(
            @UserId final Long userId,
            @PathVariable(name = "post-id") final Long id
    ) {
        PostDetailResponseDto post = postService.getPostById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponse.read(post));
    }

    @DeleteMapping("/{post-id}")
    public ResponseEntity<?> deletePostById(
            @UserId final Long userId,
            @PathVariable(name = "post-id") final Long id
    ) {
        postService.deletePost(userId, id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponse.delete());
    }

    @PatchMapping("/{post-id}")
    public ResponseEntity<ApiResponse<PostResponseDto>> updatePost(
            @UserId final Long userId,
            @PathVariable(name = "post-id") final Long id,
            @RequestBody PostRequestDto postRequestDto
    ) {
        PostResponseDto post = postService.updatePost(userId, id, postRequestDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponse.update(post));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<PageResponse<PostResponseDto>>> searchPost(
            @UserId final Long userId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        PageResponse<PostResponseDto> posts = postService.searchPosts(title, author, page, size);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponse.read(posts));
    }
}
