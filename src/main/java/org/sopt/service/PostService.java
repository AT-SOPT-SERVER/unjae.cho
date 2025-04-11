package org.sopt.service;


import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;

import java.util.List;

public class PostService {

    private final PostRepository postRepository = new PostRepository();

    public boolean createPost(final String title) {
        if (title.isEmpty() || title.length() > 30 || postRepository.checkDuplicate(title) ) {
            return false;
        } else {
            return postRepository.save(title);
        }
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(int id) {
        return postRepository.findPostById(id);
    }

    public boolean deletePostById(int id) {
        return postRepository.delete(id);
    }

    public boolean updatePostTitle(int updateId, String newTitle) {
        if (postRepository.checkDuplicate(newTitle)) {
            return false;
        }
        return postRepository.update(updateId,newTitle);
    }

    public List<Post> searchPostsByKeyword(String keyword){
        return postRepository.search(keyword);
    }
} //예외 처리 & 객체 생성?

