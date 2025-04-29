package org.sopt.global.exception;

import org.springframework.http.HttpStatus;

public enum Error {

    TITLE_EMPTY(HttpStatus.BAD_REQUEST, "게시글을 찾을 수 없습니다."),
    TOO_LONG_TITLE(HttpStatus.BAD_REQUEST, "게시글을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;

    Error(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}