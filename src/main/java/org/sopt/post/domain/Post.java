package org.sopt.post.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.sopt.comment.domain.Comment;
import org.sopt.like.domain.PostLike;
import org.sopt.user.domain.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<PostLike> likes = new ArrayList<>();

    protected Post() {}

    public Post(String title, String contents, User user) {
        this.title = title;
        this.contents = contents;
        this.user = user;
    }

    public void setPost(
            String title,
            String contents
    ){
        this.title = title;
        this.contents = contents;
    }
}
