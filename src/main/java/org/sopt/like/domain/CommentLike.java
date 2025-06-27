package org.sopt.like.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.sopt.comment.domain.Comment;
import org.sopt.user.domain.User;

@Getter
@Entity
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    protected CommentLike() {}

    public CommentLike(Comment comment, User user) {
        this.comment = comment;
        this.user = user;
    }
}
