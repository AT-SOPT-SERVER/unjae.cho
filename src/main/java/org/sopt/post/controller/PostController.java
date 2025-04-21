package org.sopt.post.controller;

import org.sopt.post.domain.Post;
import org.sopt.post.dto.PostRequest;
import org.sopt.post.service.PostService;
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
    public void createPost(@RequestBody final PostRequest postRequest) {
        postService.createPost(postRequest);
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable final Long id) {
        return postService.getPostById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePostById(@PathVariable final Long id) {
        postService.deletePost(id);
    }

    @PatchMapping("/{id}")
    public void updatePost(@PathVariable final Long id, @RequestBody PostRequest postRequest) {
        postService.updatePost(id, postRequest);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getPostsByTitle(@RequestParam String keyword) {
        return ResponseEntity.ok(postService.getPostsByTitle(keyword));
    }

}
