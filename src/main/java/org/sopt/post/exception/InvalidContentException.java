package org.sopt.post.exception;

import org.sopt.global.exception.ApiException;
import org.sopt.global.exception.ErrorCode;

public class InvalidContentException extends ApiException {
    public InvalidContentException() {
        super(ErrorCode.INVALID_CONTENT_LENGTH);
    }
}
