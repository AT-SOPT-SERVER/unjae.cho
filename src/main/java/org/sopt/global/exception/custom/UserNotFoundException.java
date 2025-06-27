package org.sopt.global.exception.custom;


import org.sopt.global.exception.ApiException;
import org.sopt.global.exception.ErrorCode;

public class UserNotFoundException extends ApiException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
