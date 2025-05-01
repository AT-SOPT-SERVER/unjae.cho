package org.sopt.post.service;

import jakarta.transaction.Transactional;
import org.sopt.post.domain.Post;
import org.sopt.post.dto.PostRequestDto;
import org.sopt.post.dto.PostResponseDto;
import org.sopt.post.exception.PostNotFoundException;
import org.sopt.post.repository.PostRepository;
import org.sopt.post.utils.PostValidator;
import org.sopt.user.domain.User;
import org.sopt.user.exception.UserNotFoundException;
import org.sopt.user.repository.UserRepository;
import org.springframework.stereotype.Service;
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

    public List<PostResponseDto> getAllPosts(
    ) {
        return postRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(PostResponseDto::new)
                .toList();
    }

    public PostResponseDto getPostById(
            Long id
    ) {
        return new PostResponseDto(postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new));   //예외 처리는 이곳!~
    }

    public void deletePost(
            Long id
    ) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);
        postRepository.delete(post);
    }

    @Transactional
    public PostResponseDto updatePost(
            Long id,
            PostRequestDto postRequest
    ) {
        PostValidator.validatePost(postRequest.title(),postRequest.content());
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);

        post.setPost(postRequest.title(),postRequest.content());
        return new PostResponseDto(postRepository.save(post));
    }

    public List<PostResponseDto> getPostsByTitle(
            String title
    ){
        return postRepository.findPostByTitleContaining(title).stream()
                .map(PostResponseDto::new)
                .toList();
    }

    public List<PostResponseDto> getPostsByUser(
            String author
    ){
        return postRepository.findPostByUser_Name(author).stream()
                .map(PostResponseDto::new)
                .toList();
    }
}
