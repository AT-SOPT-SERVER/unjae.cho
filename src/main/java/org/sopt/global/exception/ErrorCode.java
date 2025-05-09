package org.sopt.global.exception;

public enum ErrorCode {
    INVALID_TITLE_LENGTH(400, "제목이 비어있거나 30자를 넘습니다.."),
    INVALID_CONTENT_LENGTH(400, "게시글이 비어있거나 1000자를 넘습니다.."),
    INVALID_NAME_LENGTH(400, "이름이 비어있거나 10자를 넘습니다.."),
    NULL_HEADER_USERID(400, "헤더에 userId가 없습니다.."),
    USER_NOT_FOUND(404,  "사용자를 찾을 수 없습니다..!"),
    POST_NOT_FOUND(404,  "게시글을 찾을 수 없습니다..."),
    BAD_REQUEST(400,  "잘못된 요청입니다.."),
    INTERNAL_SERVER_ERROR(500,  "서버 개발자 잘못입니다..");

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() { return status; }
    public String getMessage() { return message; }
}

