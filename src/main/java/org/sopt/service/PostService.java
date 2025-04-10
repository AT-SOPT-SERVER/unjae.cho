package org.sopt.service;


import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;

import java.util.List;

public class PostService {

    private int postId = 0;

    private final PostRepository postRepository = new PostRepository();

    public boolean createPost(final String title) {
        if (title.isEmpty() || title.length() > 30 ) {
            return false;
        } else if (postRepository.checkDuplicate(title)) {
            Post post = new Post(postId++,title);     //객체 생성은 Service의 책임
            postRepository.save(post); //Post 생성 유효성 검사도 Service! => Post 에서 만들어도 되나..
            return true;
        }
        else return false;
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
    public boolean updatePostTitle(int upDateid, String newTitle) {
        return postRepository.update(upDateid,newTitle);
    }
} //예외 처리 & 객체 생성?

