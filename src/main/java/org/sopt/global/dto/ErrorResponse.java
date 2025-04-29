package org.sopt.global.dto;

public record ErrorResponse(
        String status,
        String message
) {
}