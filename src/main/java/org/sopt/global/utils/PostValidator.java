package org.sopt.global.utils;


import org.sopt.global.exception.Error;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

public class PostValidator {

    static final int MAX_TITLE = 30;

    public static boolean validatePost(String title) {
        if (Objects.equals(title, "")){
            throw new ResponseStatusException(Error.TITLE_EMPTY.getStatus(),Error.TITLE_EMPTY.getMessage());

        } else if (title.length() > MAX_TITLE) {
            throw new ResponseStatusException(Error.TOO_LONG_TITLE.getStatus(),Error.TOO_LONG_TITLE.getMessage());

        } else{
            return true;
        }
    }
}


