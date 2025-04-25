package org.sopt.post.dto;

import org.sopt.post.domain.Post;

public class PostResponse {

    private final Long id;
    private final String title;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
    }
    public PostResponse(Long id, String title) {
        this.id = id;
        this.title = title;
    }
    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
}
