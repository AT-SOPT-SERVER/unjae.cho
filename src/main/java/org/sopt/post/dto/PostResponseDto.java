package org.sopt.post.dto;

import org.sopt.post.domain.Post;

public class PostResponseDto {

    private final String title;
    private final String content;
    private final String userName; // User 전체 대신 이름만

    public PostResponseDto(
            Post post
    ) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.userName = post.getUser().getName();
    }

    public PostResponseDto(
            String title,
            String content,
            String userName
    ) {
        this.title = title;
        this.content = content;
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getUserName() {
        return userName;
    }
}
