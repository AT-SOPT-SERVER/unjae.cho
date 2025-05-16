package org.sopt.user.domain;

import jakarta.persistence.*;
import org.sopt.post.domain.Post;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Post> posts;

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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setName(String name) {
        this.name = name;
    }
}