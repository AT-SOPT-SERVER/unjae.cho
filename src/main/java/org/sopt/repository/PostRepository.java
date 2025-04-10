package org.sopt.repository;

import org.sopt.domain.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class PostRepository {

    Map<Integer, Post> postList = new HashMap<>();


    public void save(Post post) {
        postList.put(post.getId(), post);
    }

    public List<Post> findAll() {
        return new ArrayList<>(postList.values());
    }
    public Post findPostById(int id) {
        if (postList.containsKey(id)) {
                return postList.get(id);
            }

        return null;
    }
    public boolean delete(int id) {
        if (postList.containsKey(id)) {
                postList.remove(id);
                return true;
            }

        return false;
    }
    public boolean update(int id, String newTitle) {
        if (postList.containsKey(id)) {
            postList.get(id).updateTitle(newTitle);

            return true;
            }

        return false;
    }
    public boolean checkDuplicate(String title) {
        for (Post post : postList.values()) {
            if (post.getTitle().equals(title)) {
                return false;
            }

        }
        return true;
    }

}
