package org.sopt.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_URL_ERROR(404, "지원하지 않는 URL 입니다."),
    METHOD_NOT_ALLOWED_ERROR(400, "잘못된 HTTP method 요청입니다."),
    INVALID_TITLE_LENGTH(400, "제목이 비어있거나 30자를 넘습니다.."),
    INVALID_CONTENT_LENGTH(400, "게시글이 비어있거나 1000자를 넘습니다.."),
    INVALID_NAME_LENGTH(400, "이름이 비어있거나 10자를 넘습니다.."),
    NULL_HEADER_USERID(400, "헤더에 userId가 없습니다.."),
    USER_NOT_FOUND(404,  "사용자를 찾을 수 없습니다.."),
    POST_NOT_FOUND(404,  "게시글을 찾을 수 없습니다.."),
    BAD_REQUEST(400,  "잘못된 요청입니다.."),
    NOT_POST_AUTHOR(400,  "해당 게시글의 수정 권한이 없습니다.."),
    INTERNAL_SERVER_ERROR(500,  "서버 개발자 잘못입니다.."),
    COMMENT_NOT_FOUND(404, "댓글을 찾을 수 없습니다.."),
    DUPLICATE_LIKE(400, "좋아요 누른 게시글입니다.."),
    UNAUTHORIZED(403, "권한이 없습니다..");

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

}

