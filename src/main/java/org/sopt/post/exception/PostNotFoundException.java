package org.sopt.post.exception;


import org.sopt.global.exception.ApiException;
import org.sopt.global.exception.ErrorCode;

public class PostNotFoundException extends ApiException {
    public PostNotFoundException() {
        super(ErrorCode.POST_NOT_FOUND);
    }
}
