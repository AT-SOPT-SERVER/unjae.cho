package org.sopt.post.service;

import jakarta.transaction.Transactional;
import org.sopt.post.domain.Post;
import org.sopt.post.dto.PostRequestDto;
import org.sopt.post.dto.PostResponseDto;
import org.sopt.post.repository.PostRepository;
import org.sopt.post.utils.PostValidator;
import org.springframework.stereotype.Service;
import org.sopt.global.exception.Error;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(
            PostRepository postRepository
    ) {
        this.postRepository = postRepository;
    }

    @Transactional
    public PostResponseDto createPost(
            PostRequestDto postRequest
    ) {
        PostValidator.validatePost(postRequest.title());
        Post post = new Post(postRequest.title());
        postRepository.save(post);
        return new PostResponseDto(post);
    }

    public List<PostResponseDto> getAllPosts(
    ) {
        return postRepository.findAll().stream()
                .map(post -> new PostResponseDto(post.getId(), post.getTitle()))
                .toList();
    }

    public PostResponseDto getPostById(
            Long id
    ) {
        Post post = postRepository.findById(id)
                .orElseThrow(Error::BlankTitle);
        return new PostResponseDto(post.getId(), post.getTitle());

    }

    public void deletePost(
            Long id
    ) {
        postRepository.deleteById(id);
    }

    @Transactional
    public PostResponseDto updatePost(
            Long id,
            PostRequestDto postRequest
    ) {
        PostValidator.validatePost(postRequest.title());
        Post post = postRepository.findById(id)
                .orElseThrow(Error::BlankTitle);
        post.setTitle(postRequest.title());

        return new PostResponseDto(postRepository.save(post));


    }

    public List<PostResponseDto> getPostsByTitle(
            String title
    ) {
        return postRepository.findByTitleContaining(title);
    }




}
