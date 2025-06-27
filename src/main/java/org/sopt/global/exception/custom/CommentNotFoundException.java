package org.sopt.global.exception.custom;

import org.sopt.global.exception.ApiException;
import org.sopt.global.exception.ErrorCode;

public class CommentNotFoundException extends ApiException {
    public CommentNotFoundException() {
        super(ErrorCode.COMMENT_NOT_FOUND);
    }
}
