package org.sopt.repository;

import org.sopt.domain.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class PostRepository {

    private int postId = 0;
    static Map<Integer, Post> postMap = new HashMap<>();

    public boolean save(String title) {
        Post post = new Post(postId++,title);     //객체 생성은 Service의 책임
        postMap.put(post.getId(), post);
        return true;
    }

    public List<Post> findAll() {
        return new ArrayList<>(postMap.values());
    }

    public Post findPostById(int id) {
        if (postMap.containsKey(id)) {
                return postMap.get(id);
            }

        return null;
    }

    public boolean delete(int id) {
        if (postMap.containsKey(id)) {
                postMap.remove(id);
                return true;
            }

        return false;
    }

    public boolean update(int updateId, String newTitle) {
        if (postMap.containsKey(updateId)) {
            postMap.get(updateId).updateTitle(newTitle);

            return true;
            }

        return false;
    }

    public boolean checkDuplicate(String title) {   //중복 시 true !!
        for (Post post : postMap.values()) {
            if (post.getTitle().equals(title)) {
                return true;
            }

        }
        return false;
    }
    public List<Post> search(String keyword) {
        List <Post> posts = new ArrayList<>();
        for (Post post : postMap.values()) {
            if (post.getTitle().contains(keyword)) {
                posts.add(post);
            }

        }
        return posts;
    }

}
