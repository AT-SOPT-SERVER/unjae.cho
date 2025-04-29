package org.sopt.global.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class Error {

    private final HttpStatus status;
    private final String message;

    public Error(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public static ResponseStatusException BlankTitle() {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, "게시글을 찾을 수 없습니다.");
    }

    public static ResponseStatusException TooLongTitle() {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, "게시글은 30자 이내여야 합니다.");
    }

    }