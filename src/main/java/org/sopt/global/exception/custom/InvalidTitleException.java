package org.sopt.global.exception.custom;

import org.sopt.global.exception.ApiException;
import org.sopt.global.exception.ErrorCode;

public class InvalidTitleException extends ApiException {
    public InvalidTitleException() {
        super(ErrorCode.INVALID_TITLE_LENGTH);
    }
}
