package org.sopt.global.utils;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

public class PostValidator {

    static final int MAX_TITLE = 30;

    public static boolean validatePost(String title) {
        if (Objects.equals(title, "")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"게시글이 비어있으면 안됩니다.");

        } else if (title.length() > MAX_TITLE) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"게시글은 30자 이하여야 합니다.");

        } else{
            return true;
        }
    }
}


