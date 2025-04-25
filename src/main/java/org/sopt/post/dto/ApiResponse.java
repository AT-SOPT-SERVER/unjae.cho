package org.sopt.post.dto;

public record ApiResponse<T>(String status, String message, T data) {
}

