package org.sopt.global.exception.custom;

import org.sopt.global.exception.ApiException;
import org.sopt.global.exception.ErrorCode;

public class UnauthorizedException extends ApiException {
    public UnauthorizedException() {
        super(ErrorCode.UNAUTHORIZED);
    }
}
