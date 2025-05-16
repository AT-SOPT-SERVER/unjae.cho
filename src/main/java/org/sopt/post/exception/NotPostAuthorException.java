package org.sopt.post.exception;

import org.sopt.global.exception.ApiException;
import org.sopt.global.exception.ErrorCode;

public class NotPostAuthorException extends ApiException {
    public NotPostAuthorException() {
        super(ErrorCode.NOT_POST_AUTHOR);
    }
}
