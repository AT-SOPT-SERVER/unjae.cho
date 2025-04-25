package org.sopt.post.controller;

import org.sopt.post.domain.Post;
import org.sopt.post.dto.ApiResponse;
import org.sopt.post.dto.PostRequest;
import org.sopt.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody final PostRequest postRequest) {
        Post post = postService.createPost(postRequest);
        ApiResponse<Post> response = new ApiResponse<>("201", "게시글 생성", post);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        ApiResponse<List<Post>> response = new ApiResponse<>("200", "전체 게시글 조회", posts);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable final Long id) {
        Post post = postService.getPostById(id);
        ApiResponse<Post> response = new ApiResponse<>("200", "게시글 조회", post);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePostById(@PathVariable final Long id) {
        postService.deletePost(id);
        ApiResponse<Void> response = new ApiResponse<>("200","게시글 삭제", null);

        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable final Long id, @RequestBody PostRequest postRequest) {
        Post post = postService.updatePost(id, postRequest);
        ApiResponse<Post> response = new ApiResponse<>("200","게시글 수정", post);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getPostsByTitle(@RequestParam String keyword) {
        List<Post> posts = postService.getPostsByTitle(keyword);
        ApiResponse<List<Post>> response = new ApiResponse<>("200", "게시글 조회", posts);

        return ResponseEntity.ok().body(response);
    }

}
