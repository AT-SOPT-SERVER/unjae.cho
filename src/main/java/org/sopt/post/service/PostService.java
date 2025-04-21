package org.sopt.post.service;

import jakarta.transaction.Transactional;
import org.sopt.post.domain.Post;
import org.sopt.post.dto.PostRequest;
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
    public void createPost(PostRequest postRequest) {
        if(PostValidator.validatePost(postRequest.title())) {
            Post post = new Post(postRequest.title());
            postRepository.save(post);
        }
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."));
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public void updatePost(Long id,PostRequest postRequest){
        if(PostValidator.validatePost(postRequest.title())){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."));

        post.setTitle(postRequest.title());
        }
    }

    public List<Post> getPostsByTitle(String title) {
        return postRepository.findByTitleContaining(title);
    }




}
