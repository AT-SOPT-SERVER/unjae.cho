package org.sopt.post.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.sopt.comment.domain.Comment;
import org.sopt.user.domain.User;
import org.springframework.data.annotation.CreatedDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    protected Post() {}

    public Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void setPost(
            String title,
            String content
    ){
        this.title = title;
        this.content = content;
    }
}
