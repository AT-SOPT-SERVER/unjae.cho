package org.sopt.controller;

import org.sopt.domain.Post;
import org.sopt.service.PostService;
import java.util.List;
public class PostController {

    private final PostService postService = new PostService();

    public boolean createPost(final String title) {
        return postService.createPost(title);
    }

    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    public Post getPostById(final int id) {
        return postService.getPostById(id);
    }

    public boolean deletePostById(final int id) {
        return postService.deletePostById(id);
    }

    public boolean updatePostTitle(int updateId, String newTitle) {
        return postService.updatePostTitle(updateId, newTitle);
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        return postService.searchPostsByKeyword(keyword);
    }

}
