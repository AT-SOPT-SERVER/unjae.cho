package org.sopt.global.dto;

public record ApiResponse<T>(
        int status,
        String message,
        T data
) {
    public static <T> ApiResponse<T> create(T data) {
        return new ApiResponse<>(201, "생성됐습니다~!", data);
    }
    public static <T> ApiResponse<T> read(T data) {
        return new ApiResponse<>(200, "조회됐습니다~!", data);
    }
    public static <T> ApiResponse<T> update(T data) {
        return new ApiResponse<>(200, "수정됐습니다~!", data);
    }
    public static <T> ApiResponse<T> delete() {
        return new ApiResponse<>(200, "삭제됐습니다~!", null);
    }
    public static <T> ApiResponse<T> fail(int status, String message) {
        return new ApiResponse<>(status, message, null);
    }

}


