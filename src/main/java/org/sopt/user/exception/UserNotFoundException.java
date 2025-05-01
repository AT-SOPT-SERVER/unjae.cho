package org.sopt.user.exception;


import org.sopt.global.dto.ApiException;
import org.sopt.global.exception.ErrorCode;

public class UserNotFoundException extends ApiException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
