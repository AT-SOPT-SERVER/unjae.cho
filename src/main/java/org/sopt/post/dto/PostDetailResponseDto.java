package org.sopt.post.dto;

import lombok.Getter;
import org.sopt.comment.dto.CommentResponseDto;
import org.sopt.post.domain.Post;

import java.util.List;

@Getter
public class PostDetailResponseDto {

    private final String title;
    private final String content;
    private final String userName; // User 전체 대신 이름만
    private final List<CommentResponseDto> comments;
    private final int likes;

    public PostDetailResponseDto(
            Post post
    ) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.userName = post.getUser().getName();
        this.comments = post.getComments().stream()
                .map(CommentResponseDto::new)
                .toList();
        this.likes = post.getLikes().size();
    }
}
