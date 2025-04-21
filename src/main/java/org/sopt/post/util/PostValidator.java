package org.sopt.post.util;


public class PostValidator {

    static final int MAX_TITLE = 30;

    public static boolean validatePost(String title) {         // Post 유효성을 모두 체크? 이게 맞나
        if (title != "" && title.length() <= MAX_TITLE ){
            return true;
        }
        return false;
    }
}


