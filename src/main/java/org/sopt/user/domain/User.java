package org.sopt.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.sopt.post.domain.Post;

import java.time.LocalDateTime;
import java.util.List;
import org.sopt.comment.domain.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // User 탈퇴 시 인스타의 'Instagram 사용자' 처럼 게시글을 남겨두는 것도 좋지만, 추가 로직이 필요하니 일단 REMOVE로 구현
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Post> posts;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    protected User() {
    }

    public User(
            String name
    ) {
        this.name = name;
    }

    public User(
            String name,
            List<Post> posts
    ) {
        this.name = name;
        this.posts = posts;
    }
}
