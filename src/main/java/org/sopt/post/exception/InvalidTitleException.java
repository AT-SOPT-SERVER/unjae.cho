package org.sopt.post.exception;

import org.sopt.global.dto.ApiException;
import org.sopt.global.exception.ErrorCode;

public class InvalidTitleException extends ApiException {
    public InvalidTitleException() {
        super(ErrorCode.INVALID_TITLE_LENGTH);
    }
}