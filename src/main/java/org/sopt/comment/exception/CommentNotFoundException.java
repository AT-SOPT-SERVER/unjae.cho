package org.sopt.comment.exception;

import org.sopt.global.exception.ApiException;
import org.sopt.global.exception.ErrorCode;

public class CommentNotFoundException extends ApiException {
    public CommentNotFoundException() {
        super(ErrorCode.INVALID_CONTENT_LENGTH);
    }
}
