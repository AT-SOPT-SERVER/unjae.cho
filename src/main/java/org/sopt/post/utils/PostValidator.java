package org.sopt.post.utils;

import org.sopt.global.exception.Error;

public class PostValidator {

    static final int MAX_TITLE = 30;

    public static void validatePost(String title) {
        if (title == null || title.isBlank()){
            throw Error.BlankTitle();

        } else if (title.length() > MAX_TITLE) {
            throw Error.TooLongTitle();
        }
    }
}


