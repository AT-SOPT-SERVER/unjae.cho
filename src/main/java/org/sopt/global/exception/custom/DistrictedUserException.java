package org.sopt.global.exception.custom;

import org.sopt.global.exception.ApiException;
import org.sopt.global.exception.ErrorCode;

public class DistrictedUserException extends ApiException {
    public DistrictedUserException() {
        super(ErrorCode.DISTRICTED_USER);
    }
}
