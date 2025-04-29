package org.sopt.post.dto;

import org.sopt.post.domain.Post;

public class PostResponseDto {

    private final Long id;
    private final String title;
    private final String content;

    public PostResponseDto(
            Post post
    ) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }

    public PostResponseDto(
            Long id,
            String title,
            String content
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
