package org.sopt.post.service;

import jakarta.transaction.Transactional;
import org.sopt.post.domain.Post;
import org.sopt.post.dto.PostRequest;
import org.sopt.post.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void createPost(PostRequest postRequest) {
        Post post = new Post(postRequest.title());
        postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public void updatePost(Long id,PostRequest postRequest){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        post.setTitle(postRequest.title());
    }


}
