package org.sopt.user.exception;

import org.sopt.global.exception.ApiException;
import org.sopt.global.exception.ErrorCode;

public class InvalidUserNameException extends ApiException {
    public InvalidUserNameException() {
        super(ErrorCode.INVALID_NAME_LENGTH);
    }
}
