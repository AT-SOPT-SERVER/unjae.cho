package org.sopt.post.dto;

import lombok.Getter;
import org.sopt.comment.dto.CommentResponseDto;
import org.sopt.post.domain.Post;

import java.util.List;

@Getter
public class PostResponseDto {

    private final String title;
    private final String content;
    private final String userName; // User 전체 대신 이름만
    private final String tags;

    public PostResponseDto(
            Post post
    ) {
        this.title = post.getTitle();
        this.content = post.getContents();
        this.userName = post.getUser().getName();
        this.tags = post.getTags();
    }
}
