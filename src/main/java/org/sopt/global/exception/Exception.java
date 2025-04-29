package org.sopt.global.exception;

import org.springframework.http.HttpStatus;
import org.sopt.global.dto.ErrorResponse;

public abstract class Exception extends RuntimeException {

    private final Error ApiError;

    protected Exception(Error Apierror) {
        super(Apierror.getMessage());
        this.ApiError = Apierror;
    }

    public ErrorResponse getErrorResponse() {
        return new ErrorResponse("적절하지 않은 요청입니다~!", ApiError.getMessage());
    }

    public abstract HttpStatus getStatus();

}
