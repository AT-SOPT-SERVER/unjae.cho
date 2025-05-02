package org.sopt.post.dto;

public record PostRequestDto(
        String title,
        String content
) {
}            //게시글, 내용의 길이 검증은 여기가 좋나??
