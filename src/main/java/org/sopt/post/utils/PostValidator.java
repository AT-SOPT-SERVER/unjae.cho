package org.sopt.post.utils;

import org.sopt.post.exception.InvalidContentException;
import org.sopt.post.exception.InvalidTitleException;

public class PostValidator {

    static final int MAX_TITLE = 30;
    static final int MAX_CONTENT = 1000;

    public static void validatePost(String title, String content) {
        if (title == null || title.isBlank() || title.length() > MAX_TITLE){
            throw new InvalidTitleException();
        }
        else if (content == null || content.isBlank() || content.length() > MAX_CONTENT) {
            throw new InvalidContentException();
        }
    }
}
