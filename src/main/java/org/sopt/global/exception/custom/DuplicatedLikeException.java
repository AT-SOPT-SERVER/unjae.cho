package org.sopt.global.exception.custom;

import org.sopt.global.exception.ApiException;
import org.sopt.global.exception.ErrorCode;

public class DuplicatedLikeException extends ApiException {
    public DuplicatedLikeException() {
        super(ErrorCode.DUPLICATE_LIKE);
    }
}
