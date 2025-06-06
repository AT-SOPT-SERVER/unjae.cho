package org.sopt.post.controller;

import org.sopt.global.dto.ApiResponse;
import org.sopt.post.dto.PostDetailResponseDto;
import org.sopt.post.dto.PostRequestDto;
import org.sopt.post.dto.PostResponseDto;
import org.sopt.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(
            PostService postService
    ) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PostResponseDto>> createPost(
            @RequestHeader Long userId,
            @RequestBody final PostRequestDto postRequestDto
    ) {
        PostResponseDto post = postService.createPost(userId, postRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.create(post));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PostResponseDto>>> getAllPosts(
            @RequestHeader Long userId
    ) {
        List<PostResponseDto> posts = postService.getAllPosts();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponse.read(posts));
    }

    @GetMapping("/{post-id}")
    public ResponseEntity<ApiResponse<PostDetailResponseDto>> getPostById(
            @RequestHeader Long userId,
            @PathVariable(name = "post-id") final Long id
    ) {
        PostDetailResponseDto post = postService.getPostById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponse.read(post));
    }

    @DeleteMapping("/{post-id}")
    public ResponseEntity<?> deletePostById(
            @RequestHeader Long userId,
            @PathVariable(name = "post-id") final Long id
    ) {
        postService.deletePost(userId, id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponse.delete());
    }

    @PatchMapping("/{post-id}")
    public ResponseEntity<ApiResponse<PostResponseDto>> updatePost(
            @RequestHeader Long userId,
            @PathVariable(name = "post-id") final Long id,
            @RequestBody PostRequestDto postRequestDto
    ) {
        PostResponseDto post = postService.updatePost(userId, id, postRequestDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponse.update(post));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<PostResponseDto>>> searchPost(
            @RequestHeader Long userId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author
    ){
        List<PostResponseDto> posts;
        if (author == null) {
            posts = postService.getPostsByTitle(title);

        } else  {
            posts = postService.getPostsByUser(author);
        }
        return  ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponse.read(posts));
    }
}
