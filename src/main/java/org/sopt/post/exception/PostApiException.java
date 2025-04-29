package org.sopt.post.exception;
import org.springframework.http.HttpStatus;
import org.sopt.global.exception.Error;

public abstract class PostApiException extends RuntimeException {

    private final Error error;

    protected PostApiException(Error errorCode) {
        super(errorCode.getMessage());
        this.error = errorCode;
    }

    public Error getErrorCode() {
        return error;
    }

    public abstract HttpStatus getStatus(); // 상태 코드는 하위 클래스에서 결정

}
