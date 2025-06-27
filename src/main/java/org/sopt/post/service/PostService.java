package org.sopt.post.service;

import org.sopt.global.dto.PageResponse;
import org.sopt.global.exception.custom.UnauthorizedException;
import org.sopt.post.domain.Post;
import org.sopt.post.dto.PostDetailResponseDto;
import org.sopt.post.dto.PostRequestDto;
import org.sopt.post.dto.PostResponseDto;
import org.sopt.global.exception.custom.NotPostAuthorException;
import org.sopt.global.exception.custom.PostNotFoundException;
import org.sopt.post.repository.PostRepository;
import org.sopt.post.utils.PostValidator;
import org.sopt.user.domain.User;
import org.sopt.global.exception.custom.UserNotFoundException;
import org.sopt.user.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
        PostValidator.validatePost(postRequest.title(),postRequest.contents());

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        Post post = new Post(postRequest.title(), postRequest.contents(), user, postRequest.tags());

        return new PostResponseDto(postRepository.save(post));
    }

    @Transactional(readOnly = true)
    public PageResponse<PostResponseDto> getAllPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Post> postPage = postRepository.findAll(pageable);

        List<PostResponseDto> content = postPage.getContent().stream()
                .map(PostResponseDto::new)
                .toList();

        return new PageResponse<>(
                content,
                postPage.getNumber(),
                postPage.getTotalPages(),
                postPage.getTotalElements()
        );
    }

    @Transactional(readOnly = true)
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
            throw new UnauthorizedException();
        }

    }

    @Transactional
    public PostResponseDto updatePost(
            Long userId,
            Long postId,
            PostRequestDto postRequest
    ) {
        PostValidator.validatePost(postRequest.title(),postRequest.contents());

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        if (post.getUser().getId().equals(userId)) {
            post.setPost(postRequest.title(),postRequest.contents());
            return new PostResponseDto(postRepository.save(post));
        } else {
            throw new NotPostAuthorException();
        }
    }

    @Transactional(readOnly = true)
    public PageResponse<PostResponseDto> searchPosts(String title, String author, String tag, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Post> postPage = postRepository.searchPosts(title, author, tag, pageable);

        List<PostResponseDto> content = postPage.getContent().stream()
                .map(PostResponseDto::new)
                .toList();

        return new PageResponse<>(
                content,
                postPage.getNumber(),
                postPage.getTotalPages(),
                postPage.getTotalElements()
        );
    }

}
