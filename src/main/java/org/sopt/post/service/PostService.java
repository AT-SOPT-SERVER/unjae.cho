package org.sopt.post.service;

import jakarta.transaction.Transactional;
import org.sopt.post.domain.Post;
import org.sopt.post.dto.PostRequest;
import org.sopt.post.dto.PostResponse;
import org.sopt.post.repository.PostRepository;
import org.sopt.global.utils.PostValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public PostResponse createPost(PostRequest postRequest) {
        if(PostValidator.validatePost(postRequest.title())) {
            Post post = new Post(postRequest.title());
            postRepository.save(post);

            return new PostResponse(post);
        }
        return null;
    }

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> new PostResponse(post.getId(), post.getTitle()))
                .toList();
    }

    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "게시글을 찾을 수 없습니다."));
        return new PostResponse(post.getId(), post.getTitle());

    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public PostResponse updatePost(Long id,PostRequest postRequest){
        if(PostValidator.validatePost(postRequest.title())) {
            Post post = postRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "게시글을 찾을 수 없습니다."));
            post.setTitle(postRequest.title());

            return new PostResponse(postRepository.save(post));
        }
        return null;
    }

    public List<PostResponse> getPostsByTitle(String title) {
        return postRepository.findByTitleContaining(title);
    }




}
