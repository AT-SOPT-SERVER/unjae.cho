package org.sopt.like.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.sopt.post.domain.Post;
import org.sopt.user.domain.User;

@Getter
@Entity
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    protected PostLike() {}

    public PostLike(Post post, User user) {
        this.post = post;
        this.user = user;
    }
}
