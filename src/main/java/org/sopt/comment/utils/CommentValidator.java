package org.sopt.comment.utils;

import org.sopt.global.exception.custom.InvalidContentException;

public class CommentValidator {
    static final int MAX_CONTENT = 300;

    public static void validateComment(String content) {
        if (content == null || content.isBlank() || content.length() > MAX_CONTENT){
            throw new InvalidContentException();
        }
    }
}
