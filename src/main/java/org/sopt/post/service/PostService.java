package org.sopt.post.service;

import org.sopt.post.domain.Post;
import org.sopt.post.dto.PostDetailResponseDto;
import org.sopt.post.dto.PostRequestDto;
import org.sopt.post.dto.PostResponseDto;
import org.sopt.post.exception.NotPostAuthorException;
import org.sopt.post.exception.PostNotFoundException;
import org.sopt.post.repository.PostRepository;
import org.sopt.post.utils.PostValidator;
import org.sopt.user.domain.User;
import org.sopt.user.exception.UserNotFoundException;
import org.sopt.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(
            PostRepository postRepository,
            UserRepository userRepository
    ) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public PostResponseDto createPost(
            Long userId,
            PostRequestDto postRequest
            ) {
        PostValidator.validatePost(postRequest.title(),postRequest.content());
        User user = userRepository.findById(userId)     //객체 생성에 대한 분리 필요!
                .orElseThrow(UserNotFoundException::new);

        Post post = new Post(postRequest.title(), postRequest.content(), user);
        return new PostResponseDto(postRepository.save(post));
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getAllPosts(
    ) {
        return postRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(PostResponseDto::new)
                .toList();
    }

    public PostDetailResponseDto getPostById(
            Long id
    ) {
        return new PostDetailResponseDto(postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new));   //예외 처리는 이곳!~
    }

    @Transactional
    public void deletePost(
            Long userId,
            Long id
    ) {

        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);
        if (post.getUser().getId().equals(userId)) {
            postRepository.delete(post);
        } else {
            throw new NotPostAuthorException();
        }

    }

    @Transactional
    public PostResponseDto updatePost(
            Long userId,
            Long id,
            PostRequestDto postRequest
    ) {
        PostValidator.validatePost(postRequest.title(),postRequest.content());
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);

        if (post.getUser().getId().equals(userId)) {
            post.setPost(postRequest.title(),postRequest.content());
            return new PostResponseDto(postRepository.save(post));
        } else {
            throw new NotPostAuthorException();
        }

    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getPostsByTitle(
            String title
    ){
        return postRepository.findPostByTitleContaining(title).stream()
                .map(PostResponseDto::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getPostsByUser(
            String author
    ){
        User PostUser = userRepository.findByName(author);
        return postRepository.findPostByUser(PostUser).stream()
                .map(PostResponseDto::new)
                .toList();
    }
}
