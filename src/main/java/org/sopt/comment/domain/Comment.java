package org.sopt.comment.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.sopt.post.domain.Post;
import org.sopt.user.domain.User;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    private User author;

    @ManyToOne
    private Post post;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public Comment(
            String content,
            User author,
            Post post
    ) {
        this.content = content;
        this.author = author;
        this.post = post;
    }

    protected Comment() {}
}
